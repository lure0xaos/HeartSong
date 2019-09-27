package gargoyle.heartsong.params.app;

import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.util.Maps;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class BandSqlParameterSource extends MapSqlParameterSource {
    public BandSqlParameterSource(Band model) {
        super(Maps.of(
                "id", model.getId(),
                "uid", model.getUid(),
                "alias", model.getAlias(),
                "title", model.getTitle(),
                "source", model.getSource(),
                "content", model.getContent(),
                "letter", model.getLetter().getChr(),
                "genre", model.getGenre(),
                "image_type", String.valueOf(model.getImageType()),
                "image_content_length", model.getImageContentLength(),
                "image_width", model.getImageWidth(),
                "image_height", model.getImageHeight(),
                "image_path", model.getImagePath(),
                "thumbnail_type", String.valueOf(model.getThumbnailType()),
                "thumbnail_content_length", model.getThumbnailContentLength(),
                "thumbnail_width", model.getThumbnailWidth(),
                "thumbnail_height", model.getThumbnailHeight(),
                "thumbnail_path", model.getThumbnailPath()));
    }
}
