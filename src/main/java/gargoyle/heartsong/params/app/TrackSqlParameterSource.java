package gargoyle.heartsong.params.app;

import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.util.Maps;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class TrackSqlParameterSource extends MapSqlParameterSource {
    public TrackSqlParameterSource(Track model) {
        super(Maps.of(
                "id", model.getId(),
                "uid", model.getUid(),
                "alias", model.getAlias(),
                "title", model.getTitle(),
                "source", model.getSource(),
                "content", model.getContent(),
                "track_number", model.getTrackNumber(),
                "albumId", model.getAlbumId(),
                "audio_type", String.valueOf(model.getAudioType()),
                "audio_content_length", model.getAudioContentLength(),
                "audio_bitrate", model.getAudioBitrate(),
                "audio_length", null == model.getAudioLength() ? 0 : model.getAudioLength().getSeconds(),
                "audio_path", model.getAudioPath(),
                "video_type", String.valueOf(model.getVideoType()),
                "video_content_length", model.getVideoContentLength(),
                "video_length", null == model.getVideoLength() ? 0 : model.getVideoLength().getSeconds(),
                "video_width", model.getVideoWidth(),
                "video_height", model.getVideoHeight(),
                "video_path", model.getVideoPath()));
    }
}
