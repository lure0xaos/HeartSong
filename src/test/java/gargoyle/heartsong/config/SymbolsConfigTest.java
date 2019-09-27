package gargoyle.heartsong.config;

import gargoyle.heartsong.test.HSTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class SymbolsConfigTest extends HSTest {
    @Autowired
    private SymbolsConfig config;

    @Test
    public void getApplicationBuild() {
        assertNotNull("getApplicationBuild", config.getApplicationBuild());
    }

    @Test
    public void getApplicationCharset() {
        assertNotNull("getApplicationCharset", config.getApplicationCharset());
    }

    @Test
    public void getApplicationDescription() {
        assertNotNull("getApplicationDescription", config.getApplicationDescription());
    }

    @Test
    public void getApplicationInceptionYear() {
        assertNotNull("getApplicationInceptionYear", config.getApplicationInceptionYear());
    }

    @Test
    public void getApplicationName() {
        assertNotNull("getApplicationName", config.getApplicationName());
    }

    @Test
    public void getApplicationOrganizationName() {
        assertNotNull("getApplicationOrganizationName", config.getApplicationOrganizationName());
    }

    @Test
    public void getApplicationOrganizationUrl() {
        assertNotNull("getApplicationOrganizationUrl", config.getApplicationOrganizationUrl());
    }
}
