package gargoyle.heartsong.params.user;

import gargoyle.heartsong.util.Maps;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSqlParameterSource extends MapSqlParameterSource {
    public UserSqlParameterSource(UserDetails model) {
        super(Maps.of(
                "username", model.getUsername(),
                "password", model.getPassword(),
                "enabled", model.isEnabled(),
                "authorities", "ROLE_USER"));
    }
}
