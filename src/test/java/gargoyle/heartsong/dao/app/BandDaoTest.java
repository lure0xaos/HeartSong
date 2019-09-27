package gargoyle.heartsong.dao.app;

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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BandDaoTest extends HSTest {
    @Autowired
    private BandDao bandDao;


    @Before
    public void setUp() {
        for (Letter letter : Letter.values()) {
            bandDao.save(Band.builder()
                    .title(letter + " band title")
                    .source(letter + " band description")
                    .build());
        }
    }

    @Test
    public void findAll() {
        List<Band> all = bandDao.findAll();
        assertFalse("no data", null == all || all.isEmpty());
    }

    @Test
    public void countAll() {
        assertNotEquals("countAll", 0, bandDao.countAll());
    }

    @Test
    public void findById() {
        Band band = bandDao.findAll().get(0);
        Long id = band.getId();
        assertEquals("findById", bandDao.findById(id).orElse(null), band);
    }

    @Test
    public void findByAlias() {
        Band band = bandDao.findAll().get(0);
        String alias = band.getAlias();
        assertEquals("findByAlias", bandDao.findByAlias(alias).orElse(null), band);
    }

    @Test
    public void existsById() {
        Band band = bandDao.findAll().get(0);
        Long id = band.getId();
        assertTrue("existsById", bandDao.existsById(id));
    }

    @Test
    public void findAllByLetter() {
        String letterString = "A";
        List<Band> all = bandDao.findAllByLetter(letterString);
        assertFalse("no data", null == all || all.isEmpty());
    }

    @Test
    public void findAllGroupByLetter() {
        Map<Letter, List<Band>> group = bandDao.findAllGroupByLetter();
        assertNotNull("findAllGroupByLetter", group);
    }

    @Test
    public void countByLetter() {
        Map<Letter, Integer> countByLetter = bandDao.countByLetter();
        assertNotNull("findAllGroupByLetter", countByLetter);
    }

    @Test
    public void findByUid() {
        Band band = bandDao.findAll().get(0);
        String uid = band.getUid();
        assertEquals("findByUid", bandDao.findByUid(uid).orElse(null), band);
    }

    @Test
    public void save() {
        Band band = Band.builder()
                .title("band title")
                .source("band description")
                .build();
        bandDao.save(band);
        Band saved = bandDao.findByUid(band.getUid()).orElse(null);
        assertEquals("equality", saved, band);
        assertNotNull("null", saved);
        assertNotNull("no content", saved.getContent());
        assertNotNull("no letter", saved.getLetter());
        assertNotNull("no alias", saved.getAlias());
    }

    @Test
    public void search() {
        List<SearchResult<Band>> resultList = bandDao.search("band", (band) -> "");
        for (SearchResult<Band> bandSearchResult : resultList) {
            assertSame("search", Band.class, bandSearchResult.getType());
        }
    }
}
