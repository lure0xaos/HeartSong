package gargoyle.heartsong.eval;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionEvaluator {
    private final SpelParserConfiguration configuration;
    private final ExpressionParser expressionParser;
    private final ParserContext context;

    public ExpressionEvaluator() {
        configuration = new SpelParserConfiguration();
        expressionParser = new SpelExpressionParser(configuration);
        context = new ExpressionEvaluatorParserContext();
    }

    public boolean matches(String expression) {
        return null == expression || expression.isEmpty() || ExpressionUtil.isSurrounded(expression, context.getExpressionPrefix(), context.getExpressionSuffix());
    }

    public <T> T evaluate(String expression, Object root, Class<T> type) {
        if (null == expression || expression.isEmpty()) return null;
        if (matches(expression)) {
            return expressionParser.parseExpression(
                    ExpressionUtil.trim(expression, context.getExpressionPrefix(), context.getExpressionSuffix()),
                    context).getValue(root, type);
        }
        throw new IllegalArgumentException(expression);
    }

}
