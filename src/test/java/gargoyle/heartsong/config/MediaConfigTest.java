package gargoyle.heartsong.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MediaConfigTest {

    @Autowired
    private MediaConfig config;

    @Test
    public void getMediaRoot() {
        assertNotNull("getMediaRoot", config.getMediaRoot());
    }

    @Test
    public void getImageWidth() {
        assertNotEquals("getImageWidth", 0, config.getImageWidth());
    }

    @Test
    public void getImageHeight() {
        assertNotEquals("getImageHeight", 0, config.getImageHeight());
    }

    @Test
    public void getThumbnailWidth() {
        assertNotEquals("getThumbnailWidth", 0, config.getThumbnailWidth());
    }

    @Test
    public void getThumbnailHeight() {
        assertNotEquals("getThumbnailHeight", 0, config.getThumbnailHeight());
    }
}
