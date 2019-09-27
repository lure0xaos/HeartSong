package gargoyle.heartsong.dao.user;

import gargoyle.heartsong.dao.base.BaseDao;
import gargoyle.heartsong.mappers.user.SessionRowMapper;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.params.user.SessionSqlParameterSource;
import gargoyle.heartsong.util.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class SessionDao extends BaseDao<Session, String> {
    public static final String PRINCIPAL_NAME = "PRINCIPAL_NAME";
    private final PasswordEncoder passwordEncoder;

    public SessionDao(NamedParameterJdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        super(Session.class, String.class, jdbcTemplate, SessionSqlParameterSource::new, new SessionRowMapper());
        this.passwordEncoder = passwordEncoder;
    }

    public Session create(String username) {
        return new MapSession();
    }

    @Override
    public List<Session> findAll() {
        //  S left join SPRING_SESSION_ATTRIBUTES A on (S.PRIMARY_ID=A.SESSION_PRIMARY_ID)
        return queryList("select * from SPRING_SESSION order by SESSION_ID", Maps.of());
    }

    @Override
    public long countAll() {
        Optional<Long> count = queryObject("select count(SESSION_ID) from SPRING_SESSION", Maps.of(), Long.class);
        return count.orElse(0L);
    }

    public List<Session> findByPrincipalName(String username) {
        return queryList("select * from SPRING_SESSION where PRINCIPAL_NAME = :principalName limit 1", Maps.of("principalName", username));
    }

    @Override
    public Optional<Session> findById(String id) {
        return queryItem("select * from SPRING_SESSION where SESSION_ID = :id limit 1", Maps.of("id", id));
    }

    @Override
    public Optional<Session> findByUid(String uid) {
        return findById(uid);
    }

    public boolean existsByPrincipalName(String username) {
        Optional<Long> count = queryObject("select count(PRINCIPAL_NAME) from SPRING_SESSION where PRINCIPAL_NAME = :principalName", Maps.of("principalName", username), Long.class);
        return !Long.valueOf(0).equals(count.orElse(0L));
    }

    @Override
    public boolean existsById(String id) {
        return existsByPrincipalName(id);
    }

    @Transactional
    @Override
    public void save(Session model) {
        insertOrUpdate(model,
                "insert into SPRING_SESSION(SESSION_ID, CREATION_TIME, LAST_ACCESS_TIME, MAX_INACTIVE_INTERVAL, EXPIRY_TIME, PRINCIPAL_NAME) values(:id, :creationTime, :lastAccessTime, :maxInactiveInterval, :expiryTime, :principalName)",
                "update SPRING_SESSION set SESSION_ID=:id, CREATION_TIME=:creationTime, LAST_ACCESS_TIME=:lastAccessTime, MAX_INACTIVE_INTERVAL=:maxInactiveInterval, EXPIRY_TIME=:expiryTime, PRINCIPAL_NAME=:principalName where SESSION_ID=:id");
    }

    @Override
    protected boolean isNew(Session model) {
        return !existsByPrincipalName(model.getId());
    }

    @Override
    protected String getId(Session model) {
        return model.getId();
    }

    @Override
    protected void setId(Session model, String id) {
    }

    @Override
    public void delete(String id) {
        delete("delete from SPRING_SESSION where SESSION_ID=:id", id);
    }

    @Override
    public List<SearchResult<Session>> search(String query, Function<Session, String> linker) {
        return Maps.map(
                queryList("select * from SPRING_SESSION where upper(PRINCIPAL_NAME) like :query order by SESSION_ID",
                        Maps.of("query", '%' + query.toUpperCase(LocaleContextHolder.getLocale()) + '%')),
                item -> new SearchResult<>(item, modelType, item.getId(), linker.apply(item)));
    }

    public void kick(String username) {
        queryUpdate("update SPRING_SESSION set EXPIRY_TIME=LAST_ACCESS_TIME where SESSION_ID=:id", "id", username);
    }
}
