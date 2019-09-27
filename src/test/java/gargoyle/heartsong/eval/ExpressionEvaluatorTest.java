package gargoyle.heartsong.eval;

import gargoyle.heartsong.test.HSTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExpressionEvaluatorTest extends HSTest {

    private ExpressionEvaluator evaluator;

    @Before
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

    public String getTestProperty() {
        return "testProperty";
    }
}
