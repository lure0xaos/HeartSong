package gargoyle.heartsong.model.user;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MutableUser implements UserDetails {

    private final PasswordEncoder passwordEncoder;
    private final Collection<? extends GrantedAuthority> authorities;
    private String password;
    @Setter
    private String username;
    @Setter
    private boolean accountNonExpired;
    @Setter
    private boolean accountNonLocked;
    @Setter
    private boolean credentialsNonExpired;
    @Setter
    private boolean enabled;

    public MutableUser(UserDetails user, @Autowired PasswordEncoder passwordEncoder) {
        if (user != null) {
            authorities = user.getAuthorities();
            password = user.getPassword();
            username = user.getUsername();
            accountNonExpired = user.isAccountNonExpired();
            accountNonLocked = user.isAccountNonLocked();
            credentialsNonExpired = user.isCredentialsNonExpired();
            enabled = user.isEnabled();
        } else {
            authorities = new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        }
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = null == passwordEncoder ? password : passwordEncoder.encode(password);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
