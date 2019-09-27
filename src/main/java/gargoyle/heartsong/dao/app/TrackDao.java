package gargoyle.heartsong.dao.app;

import gargoyle.heartsong.dao.base.BaseDao;
import gargoyle.heartsong.mappers.app.TrackRowMapper;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.params.app.TrackSqlParameterSource;
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
public class TrackDao extends BaseDao<Track, Long> {

    private final MD md;

    public TrackDao(NamedParameterJdbcTemplate jdbcTemplate, MD md) {
        super(Track.class, Long.class, jdbcTemplate, TrackSqlParameterSource::new, new TrackRowMapper());
        this.md = md;
    }

    @Override
    public List<Track> findAll() {
        return queryList("select * from TRACKS order by ID", Maps.of());
    }

    @Override
    public long countAll() {
        Optional<Long> count = queryObject("select count(UID) from TRACKS", Maps.of(), Long.class);
        return count.orElse(0L);
    }

    @Override
    public Optional<Track> findById(Long id) {
        return queryItem("select * from TRACKS where ID = :id limit 1", Maps.of("id", id));
    }

    public Optional<Track> findByAlias(String trackAlias, String albumAlias, String bandAlias) {
        return queryItem("select T.* from TRACKS T left join ALBUMS A on T.ALBUM_ID=A.ID left join BANDS B on A.BAND_ID=B.ID where A.ALIAS = :albumAlias and B.ALIAS=:bandAlias limit 1", Maps.of("trackAlias", trackAlias, "albumAlias", albumAlias, "bandAlias", bandAlias));
    }

    @Override
    public boolean existsById(Long id) {
        Optional<Long> count = queryObject("select count(UID) from TRACKS where ID = :id", Maps.of("id", id), Long.class);
        return !Long.valueOf(0).equals(count.orElse(0L));
    }

    public List<Track> findAllByAlbumId(Long albumId) {
        return queryList("select * from TRACKS where ALBUM_ID=:albumId order by TRACK_NUMBER, ID", Maps.of("albumId", albumId));
    }

    @Override
    public Optional<Track> findByUid(String uid) {
        return queryItem("select * from TRACKS where UID = :uid limit 1", Maps.of("uid", uid));
    }

    @Transactional
    @Override
    public void save(Track model) {
        insertOrUpdate(model,
                "insert into TRACKS(UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH, AUDIO_BITRATE, AUDIO_LENGTH, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID) values(:uid, :alias, :title, :source, :content, :track_number, :audio_type, :audio_content_length, :audio_bitrate, :audio_length, :audio_path, :video_type, :video_content_length, :video_width, :video_height, :video_path, :albumId)",
                "update TRACKS set UID=:uid, ALIAS=:alias, TITLE=:title, SOURCE=:source, CONTENT=:content, TRACK_NUMBER=:track_number, AUDIO_TYPE=:audio_type, AUDIO_CONTENT_LENGTH=:audio_content_length, AUDIO_BITRATE=:audio_bitrate, AUDIO_LENGTH=:audio_length, VIDEO_PATH=:video_path, VIDEO_TYPE=:video_type, VIDEO_CONTENT_LENGTH=:video_content_length, VIDEO_WIDTH=:video_width, VIDEO_HEIGHT=:video_height, VIDEO_LENGTH=:video_length, ALBUM_ID=:albumId where ID=:id");
    }

    @Override
    public void beforeSave(Track model) {
        model.setAlias(Aliases.makeAlias(model.getTitle()));
        model.setContent(md.toHtml(model.getSource()));
        if (0 == model.getTrackNumber()) model.setTrackNumber(findAllByAlbumId(model.getAlbumId()).size());
    }

    @Override
    public List<SearchResult<Track>> search(String query, Function<Track, String> linker) {
        return Maps.map(
                queryList("select * from TRACKS where upper(TITLE) like :query or upper(CONTENT) like :query order by ID",
                        Maps.of("query", '%' + query.toUpperCase(LocaleContextHolder.getLocale()) + '%')),
                item -> new SearchResult<>(item, Track.class, item.getTitle(), linker.apply(item)));
    }

    @Override
    protected Long getId(Track model) {
        return model.getId();
    }

    @Override
    protected void setId(Track model, Long id) {
        model.setId(id);
    }

    @Override
    public void delete(Long id) {
        delete("delete from TRACKS where ID=:id", id);
    }
}
