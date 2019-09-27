package gargoyle.heartsong.dao.app;

import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Letter;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.test.HSTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TrackDaoTest extends HSTest {
    @Autowired
    private TrackDao trackDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private BandDao bandDao;
    private Band band;
    private Album album;

    @Before
    public void setUp() {
        band = Band.builder()
                .title("band title")
                .source("band description")
                .build();
        bandDao.save(band);

        album = Album.builder()
                .title("album title")
                .source("album description")
                .bandId(band.getId())
                .build();
        albumDao.save(album);

        for (Letter letter : Letter.values()) {
            trackDao.save(Track.builder()
                    .title(letter + " track title")
                    .source(letter + " track description")
                    .albumId(album.getId())
                    .build());
        }
    }

    @Test
    public void findAll() {
        List<Track> all = trackDao.findAll();
        assertFalse("no data", null == all || all.isEmpty());
    }

    @Test
    public void countAll() {
        assertNotEquals("countAll", 0, trackDao.countAll());
    }

    @Test
    public void findById() {
        Track track = trackDao.findAll().get(0);
        Long id = track.getId();
        assertEquals("findById", trackDao.findById(id).orElse(null), track);
    }

    @Test
    public void findByAlias() {
        Track track = trackDao.findAll().get(0);
        Album album = albumDao.findById(track.getAlbumId()).orElse(null);
        Band band = bandDao.findById(album.getBandId()).orElse(null);
        String bandAlias = band.getAlias();
        String albumAlias = album.getAlias();
        String trackAlias = album.getAlias();
        assertEquals("findByAlias", trackDao.findByAlias(trackAlias, albumAlias, bandAlias).orElse(null), track);
    }

    @Test
    public void existsById() {
        Long id = trackDao.findAll().get(0).getId();
        assertTrue("exist", trackDao.existsById(id));
    }

    @Test
    public void findAllByAlbumId() {
        List<Track> tracks = trackDao.findAllByAlbumId(album.getId());
        for (Track track : tracks) {
            assertEquals("findAllByAlbumId", track.getAlbumId(), album.getId());
        }
    }

    @Test
    public void findByUid() {
        Track track = trackDao.findAll().get(0);
        String uid = track.getUid();
        assertEquals("findByUid", track, trackDao.findByUid(uid).orElse(null));
    }

    @Test
    public void save() {
        Track track = Track.builder()
                .title("band title")
                .source("band description")
                .albumId(album.getId())
                .build();
        trackDao.save(track);
        assertEquals("saved expected", track, trackDao.findById(track.getId()).orElse(null));
        Track saved = trackDao.findByUid(track.getUid()).orElse(null);
        assertNotNull("null", saved);
        assertEquals("equality", saved, track);
        assertNotNull("no content", saved.getContent());
        assertNotNull("no alias", saved.getAlias());
    }

    @Test
    public void search() {
        List<SearchResult<Track>> resultList = trackDao.search("track", (track) -> "");
        for (SearchResult<Track> trackSearchResult : resultList) {
            assertSame("search", Track.class, trackSearchResult.getType());
        }
    }
}
