package ru.tmhn.summerevent.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tmhn.summerevent.model.User;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;
    private final User user;

    public UserDetailsImpl(User user) {
        username = user.getEmail();
        password = user.getPassword();
        if (user.isAdmin()) {
            authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities = List.of(new SimpleGrantedAuthority("USER"));
        }
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public User getUser() {
        return user;
    }
}
