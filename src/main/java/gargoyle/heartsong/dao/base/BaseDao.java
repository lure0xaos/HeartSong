package gargoyle.heartsong.dao.base;

import gargoyle.heartsong.util.Maps;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


public abstract class BaseDao<T, ID> implements Dao<T, ID>, Searcher<T> {
    protected final NamedParameterJdbcTemplate jdbcTemplate;
    protected final Function<T, SqlParameterSource> modelSqlParameterSourceProvider;
    protected final RowMapper<T> rowMapper;
    protected final Class<T> modelType;
    protected final Class<ID> idType;

    protected BaseDao(Class<T> modelType, Class<ID> idType,
                      NamedParameterJdbcTemplate jdbcTemplate,
                      Function<T, SqlParameterSource> parametersProvider,
                      RowMapper<T> rowMapper) {
        this.modelType = modelType;
        this.idType = idType;
        this.jdbcTemplate = jdbcTemplate;
        modelSqlParameterSourceProvider = parametersProvider;
        this.rowMapper = rowMapper;
    }

    protected SqlParameterSource provideSqlParameterSource(T model) {
        return modelSqlParameterSourceProvider.apply(model);
    }

    protected List<T> queryList(String sql, Map<String, Object> params) {
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    protected Optional<T> queryItem(String sql, Map<String, Object> paramMap) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, paramMap, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    protected <R> Optional<R> queryObject(String sql, Map<String, Object> paramMap, Class<R> resultType) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, paramMap, resultType));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    protected List<Object[]> queryObjects(String sql, Map<String, Object> paramMap, Class<Object[]> resultType) {
        return jdbcTemplate.queryForList(sql, paramMap, resultType);
    }

    protected List<Object[]> queryObjects(String sql, Map<String, Object> paramMap) {
        return jdbcTemplate.query(sql, paramMap, (rs, rowNum) -> extractArray(rs));
    }

    @SafeVarargs
    protected final Class<Object>[] queryObjectsTypes(Class<Object>... resultType) {
        return resultType;
    }

    private Object[] extractArray(ResultSet rs) throws SQLException {
        int length = rs.getMetaData().getColumnCount();
        java.lang.Object[] row = new java.lang.Object[length];
        for (int i = 0; i < length; i++) {
            row[i] = rs.getObject(i + 1);
        }
        return (Object[]) row;
    }

    protected void queryUpdate(String sql, SqlParameterSource paramSource) {
        jdbcTemplate.update(sql, paramSource);
    }

    protected ID queryInsert(String sql, SqlParameterSource paramSource) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, paramSource, keyHolder);
        return idType.cast(keyHolder.getKey());
    }

    protected void queryUpdate(String sql, Map<String, Object> paramSource) {
        queryUpdate(sql, new MapSqlParameterSource(paramSource));
    }

    protected ID queryInsert(String sql, Map<String, Object> paramSource) {
        return queryInsert(sql, new MapSqlParameterSource(paramSource));
    }

    protected void queryUpdate(String sql, Object... paramSource) {
        queryUpdate(sql, new MapSqlParameterSource(Maps.of(paramSource)));
    }

    protected ID queryInsert(String sql, Object... paramSource) {
        return queryInsert(sql, new MapSqlParameterSource(Maps.of(paramSource)));
    }

    @Transactional
    protected void insertOrUpdate(T model, String sqlInsert, String sqlUpdate) {
        beforeSave(model);
        SqlParameterSource paramSource = provideSqlParameterSource(model);
        if (isNew(model)) {
            setId(model, queryInsert(sqlInsert, paramSource));
        } else {
            queryUpdate(sqlUpdate, paramSource);
        }
        afterSave(model);
    }

    @Transactional
    protected void delete(String sqlDelete, ID id) {
        jdbcTemplate.update(sqlDelete, new MapSqlParameterSource("id", id));
    }

    protected boolean isNew(T model) {
        return null == getId(model);
    }

    protected abstract ID getId(T model);

    protected abstract void setId(T model, ID id);

    public abstract void delete(ID id);
}
