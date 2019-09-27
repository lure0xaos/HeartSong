package gargoyle.heartsong.mappers.app;

import gargoyle.heartsong.model.app.Album;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class AlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Album.builder()
                .id(rs.getLong("ID"))
                .uid(rs.getString("UID"))

                .title(rs.getString("TITLE"))
                .alias(rs.getString("ALIAS"))

                .year(0 == rs.getInt("YEAR") ? null : Year.of(rs.getInt("YEAR")))

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

                .bandId(0 == rs.getLong("BAND_ID") ? null : rs.getLong("BAND_ID"))

                .build();
    }
}
