package gargoyle.heartsong.mappers.user;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDetails> {

    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .username(rs.getString("USERNAME"))
                .password(rs.getString("PASSWORD"))

                .roles("USER")

                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!rs.getBoolean("ENABLED"))

                .build();
    }
}
