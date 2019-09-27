package gargoyle.heartsong.aspects;

import gargoyle.heartsong.annotations.AlertFailure;
import gargoyle.heartsong.annotations.AlertSuccess;
import gargoyle.heartsong.annotations.ExceptionAlert;
import gargoyle.heartsong.services.ExceptionAlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AlertExceptionAspect {

    private final ExceptionAlertService exceptionAlertService;

    @Pointcut("execution(public * *(*)) && @annotation(annotation)")
    public void pointcut(ExceptionAlert annotation) {
    }

    @Around(value = "pointcut(annotation)", argNames = "pjp,annotation")
    public Object around(ProceedingJoinPoint pjp, ExceptionAlert annotation) throws Throwable {
        if (pjp instanceof MethodInvocationProceedingJoinPoint) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            AlertSuccess success = annotation.success();
            AlertFailure failure = annotation.failure();
            return exceptionAlertService.proceed((Callable<?>) () -> {
                        try {
                            return pjp.proceed();
                        } catch (Exception exception) {
                            throw exception;
                        } catch (Throwable throwable) {
                            throw new Exception(throwable.getLocalizedMessage(), throwable);
                        }
                    },
                    LoggerFactory.getLogger(signature.getDeclaringType()),
                    failure.rethrow(), pjp.getThis(), signature.getReturnType(),
                    failure.returning(),
                    success.type(), success.message(), success.description(), success.dismissible(), success.raw(),
                    failure.type(), failure.message(), failure.description(), failure.dismissible(), failure.raw());
        } else {
            return pjp.proceed();
        }
    }


}
