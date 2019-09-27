package gargoyle.heartsong.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapsTest {

    @Test
    public void of() {
        Map<String, Integer> map = Maps.of("1", 1, "2", 2);
        assertNotNull("null", map);
        assertEquals("size", 2, map.size());
        assertEquals("1", Integer.valueOf(1), map.get("1"));
        assertEquals("2", Integer.valueOf(2), map.get("2"));
    }
}
