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

import java.time.Duration;
import java.util.UUID;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    private Long id;
    @Builder.Default
    private String uid = UUID.randomUUID().toString();

    private String alias;
    private String title;

    @Builder.Default
    private String source = "";
    @Builder.Default
    private String content = "";

    private Long albumId;

    private int trackNumber;

    @Builder.Default
    private MediaType audioType = MediaType.APPLICATION_OCTET_STREAM;
    private long audioContentLength;
    private Duration audioLength;
    private int audioBitrate;
    private String audioPath;

    @Builder.Default
    private MediaType videoType = MediaType.APPLICATION_OCTET_STREAM;
    private long videoContentLength;
    private Duration videoLength;
    private int videoWidth;
    private int videoHeight;
    private String videoPath;


}
