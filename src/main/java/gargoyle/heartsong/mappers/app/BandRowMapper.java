package gargoyle.heartsong.mappers.app;

import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Letter;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BandRowMapper implements RowMapper<Band> {

    @Override
    public Band mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Band.builder()
                .id((rs.getLong("ID")))
                .uid((rs.getString("UID")))

                .title(rs.getString("TITLE"))
                .letter(Letter.forTitle(rs.getString("TITLE")))
                .alias(rs.getString("ALIAS"))

                .genre(rs.getString("GENRE"))

                .source(rs.getString("SOURCE"))
                .content(rs.getString("CONTENT"))

                .imagePath(rs.getString("IMAGE_PATH"))
                .imageContentLength(rs.getLong("IMAGE_CONTENT_LENGTH"))
                .imageType(MediaType.valueOf(rs.getString("IMAGE_TYPE")))
                .imageWidth(rs.getInt("IMAGE_WIDTH"))
                .imageHeight(rs.getInt("IMAGE_HEIGHT"))

                .thumbnailPath(rs.getString("THUMBNAIL_PATH"))
                .thumbnailContentLength(rs.getLong("THUMBNAIL_CONTENT_LENGTH"))
                .thumbnailType(MediaType.valueOf(rs.getString("THUMBNAIL_TYPE")))
                .thumbnailWidth(rs.getInt("THUMBNAIL_WIDTH"))
                .thumbnailHeight(rs.getInt("THUMBNAIL_HEIGHT"))

                .build();
    }
}
