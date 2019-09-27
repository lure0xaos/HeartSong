package gargoyle.heartsong.mappers.user;

import gargoyle.heartsong.dao.user.SessionDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.session.MapSession;
import org.springframework.session.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class SessionRowMapper implements RowMapper<Session> {

    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        MapSession session = new MapSession(rs.getString("SESSION_ID"));
        session.setCreationTime(Instant.ofEpochMilli(rs.getLong("CREATION_TIME")));
        session.setLastAccessedTime(Instant.ofEpochMilli(rs.getLong("LAST_ACCESS_TIME")));
        session.setMaxInactiveInterval(Duration.ofSeconds(rs.getLong("MAX_INACTIVE_INTERVAL")));
        session.setAttribute(SessionDao.PRINCIPAL_NAME, rs.getString("PRINCIPAL_NAME"));
        return session;
    }
}
