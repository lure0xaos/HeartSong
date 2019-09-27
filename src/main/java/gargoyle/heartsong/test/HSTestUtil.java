package gargoyle.heartsong.test;

import gargoyle.heartsong.dao.app.AlbumDao;
import gargoyle.heartsong.dao.app.BandDao;
import gargoyle.heartsong.dao.app.TrackDao;
import gargoyle.heartsong.dao.base.Dao;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Letter;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.services.MD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;

@Slf4j
public final class HSTestUtil {

    private static final String SUFFIX;

    static {
        String[] a = new String[25];
        Arrays.fill(a, "description");
        SUFFIX = String.join("\n", a);
    }

    private HSTestUtil() {
    }


    public static void prepareData(DataSource dataSource) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MD md = new MD();
        Dao<Band, Long> bandDao = new BandDao(jdbcTemplate, md);
        Dao<Album, Long> albumDao = new AlbumDao(jdbcTemplate, md);
        Dao<Track, Long> trackDao = new TrackDao(jdbcTemplate, md);
        Letter[] letters = {Letter.A, Letter.B, Letter.C};
        int lettersLength = letters.length;
        if (0 == bandDao.countAll()) {
            for (Letter bandLetter : letters) {
                String bandType = Band.class.getSimpleName();
                String bandTitle = title(bandLetter, bandType, "");
                String bandDescription = description(bandLetter, bandType);
                Band band = Band.builder().title(bandTitle).source(bandDescription).build();
                bandDao.save(band);
                for (Letter albumLetter : letters) {
                    String albumType = Album.class.getSimpleName();
                    String albumTitle = title(albumLetter, albumType, " - " + bandTitle);
                    String albumDescription = description(albumLetter, albumType);
                    Album album = Album.builder().title(albumTitle).source(albumDescription).bandId(band.getId()).build();
                    albumDao.save(album);
                    for (int t = 0; t < lettersLength; t++) {
                        Letter trackLetter = letters[t];
                        String trackType = Track.class.getSimpleName();
                        String trackTitle = title(trackLetter, trackType, " - " + albumTitle);
                        String trackDescription = description(trackLetter, trackType);
                        Track track = Track.builder().title(trackTitle).source(trackDescription).trackNumber(t + 1).albumId(album.getId()).build();
                        trackDao.save(track);
                        log.info("saved {} {} {}.{}", band.getTitle(), album.getTitle(), track.getTrackNumber(), track.getTitle());
                    }
                }
            }
        }
    }

    private static String title(Letter letter, String type, String suffix) {
        return letter + " " + type + suffix;
    }

    private static String description(Letter letter, String type) {
        return title(letter, type, SUFFIX);
    }
}
