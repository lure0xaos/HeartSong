package gargoyle.heartsong.dao.user;

import gargoyle.heartsong.dao.base.BaseDao;
import gargoyle.heartsong.mappers.user.UserRowMapper;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.model.user.MutableUser;
import gargoyle.heartsong.params.user.UserSqlParameterSource;
import gargoyle.heartsong.util.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class UserDao extends BaseDao<UserDetails, String> {
    private final PasswordEncoder passwordEncoder;

    public UserDao(NamedParameterJdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        super(UserDetails.class, String.class, jdbcTemplate, UserSqlParameterSource::new, new UserRowMapper());
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails create(String username, String password, String... roles) {
        return User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username(username)
                .password(password)

                .roles(roles)

                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)

                .build();
    }

    @Override
    public List<UserDetails> findAll() {
        return queryList("select * from USERS order by USERNAME", Maps.of());
    }

    @Override
    public long countAll() {
        Optional<Long> count = queryObject("select count(USERNAME) from USERS", Maps.of(), Long.class);
        return count.orElse(0L);
    }

    public Optional<UserDetails> findByUsername(String username) {
        return queryItem("select * from USERS where USERNAME = :username limit 1", Maps.of("username", username));
    }

    @Override
    public Optional<UserDetails> findById(String id) {
        return findByUsername(id);
    }

    @Override
    public Optional<UserDetails> findByUid(String uid) {
        return findByUsername(uid);
    }

    public boolean existsByUsername(String username) {
        Optional<Long> count = queryObject("select count(USERNAME) from USERS where USERNAME = :username", Maps.of("username", username), Long.class);
        return !Long.valueOf(0).equals(count.orElse(0L));
    }

    @Override
    public boolean existsById(String id) {
        return existsByUsername(id);
    }

    @Transactional
    public void changePassword(String username, String newPassword) {
        queryUpdate("update USERS set PASSWORD=:password where USERNAME=:username", new MapSqlParameterSource(Maps.of("password", passwordEncoder.encode(newPassword), "username", username)));
    }

    @Transactional
    @Override
    public void save(UserDetails model) {
        insertOrUpdate(model,
                "insert into USERS(USERNAME, PASSWORD, ENABLED) values(:username, :password, :enabled)",
                "update USERS set USERNAME=:username, PASSWORD=:password, ENABLED=:enabled where USERNAME=:username");
    }

    @Override
    protected boolean isNew(UserDetails model) {
        return !existsByUsername(model.getUsername());
    }

    @Override
    protected String getId(UserDetails model) {
        return model.getUsername();
    }

    @Override
    protected void setId(UserDetails model, String id) {
    }

    @Override
    public void delete(String id) {
        delete("delete from USERS where username=:id", id);
    }

    @Override
    public List<SearchResult<UserDetails>> search(String query, Function<UserDetails, String> linker) {
        return Maps.map(
                queryList("select * from USERS where upper(USERNAME) like :query order by USERNAME",
                        Maps.of("query", '%' + query.toUpperCase(LocaleContextHolder.getLocale()) + '%')),
                item -> new SearchResult<>(item, modelType, item.getUsername(), linker.apply(item)));
    }

    public UserDetails create() {
        return new MutableUser(null, passwordEncoder);
    }
}
