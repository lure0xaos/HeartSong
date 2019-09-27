package gargoyle.heartsong.services;

import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.test.HSTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class SearchTest extends HSTest {
    @Autowired
    private AppService search;

    @Test
    public void search() {
        List<SearchResult<?>> searchResults = search.search("title");
        assertNotNull("search", searchResults);
        log.info(searchResults.toString());
    }
}
