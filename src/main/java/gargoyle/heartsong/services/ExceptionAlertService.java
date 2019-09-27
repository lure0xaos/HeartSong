package gargoyle.heartsong.services;

import gargoyle.heartsong.eval.ExpressionEvaluator;
import gargoyle.heartsong.model.alerts.Alert;
import gargoyle.heartsong.model.alerts.AlertType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExceptionAlertService {

    private final Alerts alerts;
    private final MessageSource messageSource;
    private final ConversionService conversion;
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    public <T> T proceed(Callable<? extends T> proceed, Logger logger, boolean failureRethrow,
                         Object root, Class<T> returnType, String failureReturning,
                         AlertType successType, String successMessage, String successDescription,
                         boolean successDismissible, boolean successRaw,
                         AlertType failureType, String failureMessage, String failureDescription,
                         boolean failureDismissible, boolean failureRaw) throws Exception {
        return proceed(proceed, logger,
                failureRethrow, convert(returnType, evalAsObject(failureReturning, root, returnType)),
                result -> Alert.builder()
                        .type(successType)
                        .message(evalAsString(successMessage, root))
                        .description(evalAsString(successDescription, root))
                        .dismissible(successDismissible)
                        .raw(successRaw)
                        .build(),
                exception -> Alert.builder()
                        .type(failureType)
                        .message(failureMessage.isEmpty() ? null : evalAsString(failureMessage, root))
                        .description(evalAsString(failureDescription, root))
                        .dismissible(failureDismissible)
                        .raw(failureRaw)
                        .build());
    }

    public <T> T proceed(Callable<? extends T> proceed, Logger logger, boolean failureRethrow, T failureReturning,
                         Function<? super T, ? extends Alert> successFunction,
                         Function<? super Exception, ? extends Alert> failureFunction) throws Exception {
        try {
            T result = proceed.call();
            if (null != successFunction) {
                alerts.alert(successFunction.apply(result));
            }
            return result;
        } catch (Exception exception) {
            if (null != failureFunction) {
                Alert failure = failureFunction.apply(exception);
                if (isEmpty(failure.getMessage())) {
                    failure.setMessage(getExceptionMessage(exception));
                }
                alerts.alert(failure);
            }
            logger.error(exception.getLocalizedMessage(), exception);
            if (failureRethrow) throw exception;
            return failureReturning;
        }
    }

    private <T> T convert(Class<T> targetType, Object source) {
        if (null == source) return null;
        if (conversion.canConvert(source.getClass(), targetType)) return conversion.convert(source, targetType);
        return null;
    }

    private boolean isEmpty(String text) {
        return null == text || text.isEmpty();
    }

    private String getExceptionMessage(Throwable throwable) {
        String message = throwable.getClass().getSimpleName()
                .replaceAll("[A-Z]", " $0")
                .replace("Exception", "");
        String description = throwable.getLocalizedMessage();
        if (null == description) return message;
        return message + ": " + description;
    }

    private <T> String evalAsString(String expression, Object root) {
        if (evaluator.matches(expression))
            return asString(evaluator.evaluate(expression, root, Object.class));
        return expression;
    }

    private String asString(Object value) {
        return null == value ? "" : value.toString();
    }

    private <T> T evalAsObject(String expression, Object root, Class<T> type) {
        return evaluator.matches(expression) ? evaluator.evaluate(expression, root, type) : null;
    }
}
