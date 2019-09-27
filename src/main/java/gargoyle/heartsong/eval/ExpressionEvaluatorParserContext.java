package gargoyle.heartsong.eval;

import org.springframework.expression.ParserContext;

public class ExpressionEvaluatorParserContext implements ParserContext {
    public static final String EXPRESSION_PREFIX = "${";
    public static final String EXPRESSION_SUFFIX = "}";

    @Override
    public boolean isTemplate() {
        return false;
    }

    @Override
    public String getExpressionPrefix() {
        return EXPRESSION_PREFIX;
    }

    @Override
    public String getExpressionSuffix() {
        return EXPRESSION_SUFFIX;
    }
}
