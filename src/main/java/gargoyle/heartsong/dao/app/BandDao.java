package gargoyle.heartsong.dao.app;

import gargoyle.heartsong.dao.base.BaseDao;
import gargoyle.heartsong.mappers.app.BandRowMapper;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Letter;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.params.app.BandSqlParameterSource;
import gargoyle.heartsong.services.MD;
import gargoyle.heartsong.util.Aliases;
import gargoyle.heartsong.util.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BandDao extends BaseDao<Band, Long> {

    private final MD md;

    public BandDao(NamedParameterJdbcTemplate jdbcTemplate, MD md) {
        super(Band.class, Long.class, jdbcTemplate, BandSqlParameterSource::new, new BandRowMapper());
        this.md = md;
    }

    @Override
    public List<Band> findAll() {
        return queryList("select * from BANDS order by ID", Maps.of());
    }

    @Override
    public long countAll() {
        Optional<Long> count = queryObject("select count(UID) from BANDS", Maps.of(), Long.class);
        return count.orElse(0L);
    }

    @Override
    public Optional<Band> findById(Long id) {
        return queryItem("select * from BANDS where ID = :id limit 1", Maps.of("id", id));
    }

    public Optional<Band> findByAlias(String bandAlias) {
        return queryItem("select * from BANDS where ALIAS = :bandAlias limit 1",
                Maps.of("bandAlias", bandAlias));
    }

    @Override
    public boolean existsById(Long id) {
        Optional<Long> count = queryObject("select count(UID) from BANDS where ID = :id", Maps.of("id", id), Long.class);
        return !Long.valueOf(0).equals(count.orElse(0L));
    }

    public List<Band> findAllByLetter(String letter) {
        return queryList("select * from BANDS where LETTER = :letter order by ID", Maps.of("letter", letter));
    }

    public Map<Letter, List<Band>> findAllGroupByLetter() {
        return findAll().stream().collect(
                Collectors.groupingBy(Band::getLetter, () -> new EnumMap<>(Letter.class), Collectors.toList()));
    }

    public Map<Letter, Integer> countByLetter() {
        Map<Letter, Integer> map = Arrays.stream(Letter.values()).collect(
                Collectors.toMap(letter -> letter, letter -> 0, (a, i) -> i, () -> new EnumMap<>(Letter.class)));
        queryObjects("select LETTER, count(LETTER) from BANDS group by(LETTER)", Maps.of())
                .forEach(tuple -> map.put(
                        Letter.forChar(String.valueOf(tuple[0])),
                        Integer.valueOf(String.valueOf(tuple[1]))));
        return map;
    }

    @Override
    public Optional<Band> findByUid(String uid) {
        return queryItem("select * from BANDS where UID = :uid limit 1", Maps.of("uid", uid));
    }

    @Transactional
    @Override
    public void save(Band model) {
        insertOrUpdate(model,
                "insert into BANDS(UID, ALIAS, TITLE, SOURCE, CONTENT, LETTER, GENRE, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, THUMBNAIL_PATH) values(:uid, :alias, :title, :source, :content, :letter, :genre, :image_type, :image_content_length, :image_width, :image_height, :image_path, :thumbnail_type, :thumbnail_content_length, :thumbnail_width, :thumbnail_height, :thumbnail_path)",
                "update BANDS set UID=:uid, ALIAS=:alias, TITLE=:title, SOURCE=:source, CONTENT=:content, LETTER=:letter, GENRE=:genre, IMAGE_TYPE=:image_type, IMAGE_CONTENT_LENGTH=:image_content_length, IMAGE_WIDTH=:image_width, IMAGE_HEIGHT=:image_height, IMAGE_PATH=:image_path, THUMBNAIL_TYPE=:thumbnail_type, THUMBNAIL_CONTENT_LENGTH=:thumbnail_content_length, THUMBNAIL_WIDTH=:thumbnail_width, THUMBNAIL_HEIGHT=:thumbnail_height, THUMBNAIL_PATH=:thumbnail_path where ID=:id");
    }


    @Override
    public void beforeSave(Band model) {
        model.setAlias(Aliases.makeAlias(model.getTitle()));
        model.setContent(md.toHtml(model.getSource()));
        model.setLetter(Letter.forTitle(model.getTitle()));
    }

    @Override
    public List<SearchResult<Band>> search(String query, Function<Band, String> linker) {
        return Maps.map(
                queryList("select * from BANDS where upper(TITLE) like :query or upper(CONTENT) like :query order by ID",
                        Maps.of("query", '%' + query.toUpperCase(LocaleContextHolder.getLocale()) + '%')),
                item -> new SearchResult<>(item, Band.class, item.getTitle(), linker.apply(item)));
    }

    @Override
    protected Long getId(Band model) {
        return model.getId();
    }

    @Override
    protected void setId(Band model, Long id) {
        model.setId(id);
    }

    @Override
    public void delete(Long id) {
        delete("delete from BANDS where ID=:id", id);
    }
}
