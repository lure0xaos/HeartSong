package gargoyle.heartsong.config;

import gargoyle.heartsong.test.HSTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SymbolsConfigTest extends HSTest {
    @Autowired
    private SymbolsConfig config;

    @Test
    public void getApplicationBuild() {
        assertNotNull(config.getApplicationBuild(), "getApplicationBuild");
    }

    @Test
    public void getApplicationCharset() {
        assertNotNull(config.getApplicationCharset(), "getApplicationCharset");
    }

    @Test
    public void getApplicationDescription() {
        assertNotNull(config.getApplicationDescription(), "getApplicationDescription");
    }

    @Test
    public void getApplicationInceptionYear() {
        assertNotNull(config.getApplicationInceptionYear(), "getApplicationInceptionYear");
    }

    @Test
    public void getApplicationName() {
        assertNotNull(config.getApplicationName(), "getApplicationName");
    }

    @Test
    public void getApplicationOrganizationName() {
        assertNotNull(config.getApplicationOrganizationName(), "getApplicationOrganizationName");
    }

    @Test
    public void getApplicationOrganizationUrl() {
        assertNotNull(config.getApplicationOrganizationUrl(), "getApplicationOrganizationUrl");
    }
}
