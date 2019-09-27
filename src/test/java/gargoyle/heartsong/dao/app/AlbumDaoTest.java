package gargoyle.heartsong.dao.app;

import com.mchange.util.AssertException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Letter;
import gargoyle.heartsong.model.app.SearchResult;
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
public class AlbumDaoTest extends HSTest {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private BandDao bandDao;

    private Band band;

    @Before
    public void setUp() {
        band = Band.builder()
                .title("band title")
                .source("band description")
                .build();
        bandDao.save(band);

        for (Letter letter : Letter.values()) {
            albumDao.save(Album.builder()
                    .title(letter + " album title")
                    .source(letter + " album description")
                    .bandId(band.getId())
                    .build());
        }
    }

    @Test
    public void findAll() {
        List<Album> all = albumDao.findAll();
        assertFalse("no data", null == all || all.isEmpty());
    }

    @Test
    public void countAll() {
        assertNotEquals("countAll", 0, albumDao.countAll());
    }

    @Test
    public void findById() {
        Album album = albumDao.findAll().get(0);
        Long id = album.getId();
        assertEquals("findById", albumDao.findById(id).orElseThrow(() -> new AssertException("not saved")), album);
    }

    @Test
    public void findByAlias() {
        Album album = albumDao.findAll().get(0);
        Band band = bandDao.findById(album.getBandId()).orElse(null);
        String bandAlias = band.getAlias();
        String albumAlias = album.getAlias();
        assertEquals("findByAlias", albumDao.findByAlias(albumAlias, bandAlias).orElse(null), album);
    }

    @Test
    public void existsById() {
        Long id = albumDao.findAll().get(0).getId();
        assertTrue("exist", albumDao.existsById(id));
    }

    @Test
    public void findAllByBandId() {
        List<Album> albums = albumDao.findAllByBandId(band.getId());
        for (Album album : albums) {
            assertEquals("", album.getBandId(), band.getId());
        }
    }

    @Test
    public void findByUid() {
        Album album = albumDao.findAll().get(0);
        String uid = album.getUid();
        assertEquals("findByUid", album, albumDao.findByUid(uid).orElse(null));
    }

    @Test
    public void save() {
        Album album = Album.builder()
                .title("Album title")
                .source("Album description")
                .bandId(band.getId())
                .build();
        albumDao.save(album);
        assertEquals("saved expected", album, albumDao.findById(album.getId()).orElse(null));
        Album saved = albumDao.findByUid(album.getUid()).orElse(null);
        assertNotNull("null", saved);
        assertEquals("equality", saved, album);
        assertNotNull("no content", saved.getContent());
        assertNotNull("no alias", saved.getAlias());
    }

    @Test
    public void search() {
        List<SearchResult<Album>> resultList = albumDao.search("album", (album -> ""));
        for (SearchResult<Album> albumSearchResult : resultList) {
            assertSame("search", Album.class, albumSearchResult.getType());
        }
    }
}
