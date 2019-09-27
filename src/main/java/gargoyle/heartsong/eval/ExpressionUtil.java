package gargoyle.heartsong.eval;

public final class ExpressionUtil {
    private ExpressionUtil() {
    }

    public static String trim(String expression, String expressionPrefix, String expressionSuffix) {
        return isSurrounded(expression, expressionPrefix, expressionSuffix) ? expression.substring(expressionPrefix.length(), expression.length() - expressionSuffix.length()) : expression;
    }

    public static boolean isSurrounded(String expression, String expressionPrefix, String expressionSuffix) {
        return expression.startsWith(expressionPrefix) && expression.endsWith(expressionSuffix);
    }
}
