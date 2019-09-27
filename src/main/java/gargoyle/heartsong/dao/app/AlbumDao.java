package gargoyle.heartsong.dao.app;

import gargoyle.heartsong.dao.base.BaseDao;
import gargoyle.heartsong.mappers.app.AlbumRowMapper;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.params.app.AlbumSqlParameterSource;
import gargoyle.heartsong.services.MD;
import gargoyle.heartsong.util.Aliases;
import gargoyle.heartsong.util.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class AlbumDao extends BaseDao<Album, Long> {

    private final MD md;

    public AlbumDao(NamedParameterJdbcTemplate jdbcTemplate, MD md) {
        super(Album.class, Long.class, jdbcTemplate, AlbumSqlParameterSource::new, new AlbumRowMapper());
        this.md = md;
    }

    @Override
    public List<Album> findAll() {
        return queryList("select * from ALBUMS order by ID", Maps.of());
    }

    @Override
    public long countAll() {
        Optional<Long> count = queryObject("select count(UID) from ALBUMS", Maps.of(), Long.class);
        return count.orElse(0L);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return queryItem("select * from ALBUMS where ID = :id limit 1", Maps.of("id", id));
    }

    public Optional<Album> findByAlias(String albumAlias, String bandAlias) {
        return queryItem(
                "select A.* from ALBUMS A left join BANDS B on A.BAND_ID=B.ID where A.ALIAS = :albumAlias and B.ALIAS=:bandAlias limit 1",
                Maps.of("albumAlias", albumAlias, "bandAlias", bandAlias));
    }

    @Override
    public boolean existsById(Long id) {
        Optional<Long> count = queryObject("select count(UID) from ALBUMS where ID = :id", Maps.of("id", id), Long.class);
        return !Long.valueOf(0).equals(count.orElse(0L));
    }

    public List<Album> findAllByBandId(Long bandId) {
        return queryList("select * from ALBUMS where BAND_ID=:bandId order by YEAR, ID", Maps.of("bandId", bandId));
    }

    @Override
    public Optional<Album> findByUid(String uid) {
        return queryItem("select * from ALBUMS where UID = :uid limit 1", Maps.of("uid", uid));
    }

    @Transactional
    @Override
    public void save(Album model) {
        insertOrUpdate(model,
                "insert into ALBUMS(UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID) values(:uid, :alias, :title, :source, :content, :year, :image_type, :image_content_length, :image_width, :image_height, :image_path, :thumbnail_type, :thumbnail_content_length, :thumbnail_width, :thumbnail_height, :thumbnail_path, :bandId)",
                "update ALBUMS set UID=:uid, ALIAS=:alias, TITLE=:title, SOURCE=:source, CONTENT=:content, YEAR=:year, IMAGE_TYPE=:image_type, IMAGE_CONTENT_LENGTH=:image_content_length, IMAGE_WIDTH=:image_width, IMAGE_HEIGHT=:image_height, IMAGE_PATH=:image_path, THUMBNAIL_TYPE=:thumbnail_type, THUMBNAIL_CONTENT_LENGTH=:thumbnail_content_length, THUMBNAIL_WIDTH=:thumbnail_width, THUMBNAIL_HEIGHT=:thumbnail_height, THUMBNAIL_PATH=:thumbnail_path, BAND_ID=:bandId where ID=:id");
    }

    @Override
    public void beforeSave(Album model) {
        model.setAlias(Aliases.makeAlias(model.getTitle()));
        model.setContent(md.toHtml(model.getSource()));
    }

    @Override
    public List<SearchResult<Album>> search(String query, Function<Album, String> linker) {
        return Maps.map(
                queryList("select * from ALBUMS where upper(TITLE) like :query or upper(CONTENT) like :query order by ID",
                        Maps.of("query", '%' + query.toUpperCase(LocaleContextHolder.getLocale()) + '%')),
                item -> new SearchResult<>(item, Album.class, item.getTitle(), linker.apply(item)));
    }

    @Override
    protected Long getId(Album model) {
        return model.getId();
    }

    @Override
    protected void setId(Album model, Long id) {
        model.setId(id);
    }

    @Override
    public void delete(Long id) {
        delete("delete from ALBUMS", id);
    }
}
