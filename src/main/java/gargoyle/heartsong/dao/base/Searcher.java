package gargoyle.heartsong.dao.base;

import gargoyle.heartsong.model.app.SearchResult;

import java.util.List;
import java.util.function.Function;

public interface Searcher<T> {

    List<SearchResult<T>> search(String query, Function<T, String> linker);
}
