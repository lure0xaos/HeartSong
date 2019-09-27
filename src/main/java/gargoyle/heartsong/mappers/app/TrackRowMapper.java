package gargoyle.heartsong.mappers.app;

import gargoyle.heartsong.model.app.Track;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TrackRowMapper implements RowMapper<Track> {

    @Override
    public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Track.builder()
                .id((rs.getLong("ID")))
                .uid((rs.getString("UID")))

                .title(rs.getString("TITLE"))
                .alias(rs.getString("ALIAS"))

                .trackNumber(rs.getInt("TRACK_NUMBER"))

                .source(rs.getString("SOURCE"))
                .content((rs.getString("CONTENT")))

                .audioPath(rs.getString("AUDIO_PATH"))
                .audioContentLength(rs.getLong("AUDIO_CONTENT_LENGTH"))
                .audioType(MediaType.valueOf(rs.getString("AUDIO_TYPE")))
                .audioBitrate(rs.getInt("AUDIO_BITRATE"))
                .audioLength(0 == rs.getLong("AUDIO_LENGTH") ? null : Duration.of(rs.getLong("AUDIO_LENGTH"), ChronoUnit.SECONDS))

                .videoPath(rs.getString("VIDEO_PATH"))
                .videoContentLength(rs.getLong("VIDEO_CONTENT_LENGTH"))
                .videoType(MediaType.valueOf(rs.getString("VIDEO_TYPE")))
                .videoWidth(rs.getInt("VIDEO_WIDTH"))
                .videoHeight(rs.getInt("VIDEO_HEIGHT"))
                .videoLength(0 == rs.getLong("VIDEO_LENGTH") ? null : Duration.of(rs.getLong("VIDEO_LENGTH"), ChronoUnit.SECONDS))

                .albumId(0 == rs.getLong("ALBUM_ID") ? null : rs.getLong("ALBUM_ID"))

                .build();
    }
}
