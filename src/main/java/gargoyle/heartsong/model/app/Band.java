package gargoyle.heartsong.model.app;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;

import java.util.UUID;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Band {

    private Long id;
    @Builder.Default
    private String uid = UUID.randomUUID().toString();

    private String alias;
    private String title;

    @Builder.Default
    private String source = "";
    @Builder.Default
    private String content = "";

    private Letter letter;
    private String genre;

    @Builder.Default
    private MediaType imageType = MediaType.APPLICATION_OCTET_STREAM;
    private long imageContentLength;
    private int imageWidth;
    private int imageHeight;
    private String imagePath;

    @Builder.Default
    private MediaType thumbnailType = MediaType.APPLICATION_OCTET_STREAM;
    private long thumbnailContentLength;
    private int thumbnailWidth;
    private int thumbnailHeight;
    private String thumbnailPath;

}
