package gargoyle.heartsong.params.user;

import gargoyle.heartsong.dao.user.SessionDao;
import gargoyle.heartsong.util.Maps;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.session.Session;

public class SessionSqlParameterSource extends MapSqlParameterSource {
    public SessionSqlParameterSource(Session model) {
        super(Maps.of(
                "id", model.getId(),
                "creationTime", model.getCreationTime().toEpochMilli(),
                "maxInactiveInterval", model.getMaxInactiveInterval().getSeconds(),
                "lastAccessTime", model.getLastAccessedTime().toEpochMilli(),
                "expiryTime", model.getLastAccessedTime().toEpochMilli(),
                "principalName", model.getAttribute(SessionDao.PRINCIPAL_NAME)
        ));
    }
}
