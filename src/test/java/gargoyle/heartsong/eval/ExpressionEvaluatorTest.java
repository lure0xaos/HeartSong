package gargoyle.heartsong.eval;

import gargoyle.heartsong.test.HSTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class ExpressionEvaluatorTest extends HSTest {

    private ExpressionEvaluator evaluator;

    @BeforeEach
    public void setUp() {
        evaluator = new ExpressionEvaluator();
    }

    @Test
    public void matches() {
        String expr = "${testProperty}";
        assertTrue(expr, evaluator.matches(expr));
    }

    @Test
    public void eval() {
        String expr = "${testProperty}";
        assertNotNull(expr, evaluator.evaluate(expr, this, String.class));
    }

    @SuppressWarnings("unused")
    public String getTestProperty() {
        return "testProperty";
    }
}
