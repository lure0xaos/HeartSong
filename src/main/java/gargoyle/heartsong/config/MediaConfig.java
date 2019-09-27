package gargoyle.heartsong.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:media.properties")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MediaConfig {
    @Value("${media.root}")
    private Resource mediaRoot;
    @Value("${media.missing}")
    private Resource missingImage;
    @Value("${media.image.width}")
    private int imageWidth;
    @Value("${media.image.height}")
    private int imageHeight;
    @Value("${media.thumbnail.width}")
    private int thumbnailWidth;
    @Value("${media.thumbnail.height}")
    private int thumbnailHeight;
}
