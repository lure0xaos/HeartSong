package gargoyle.heartsong.util;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


public class MapsTest {

    @Test
    public void of() {
        Map<String, Integer> map = Maps.of("1", 1, "2", 2);
        assertNotNull("null", map);
        assertEquals("size", 2, map.size());
        assertEquals("1", 1, map.get("1"));
        assertEquals("2", 2, map.get("2"));
    }
}
