package ru.erogov.simplerestservice.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

public final class User implements UserDetails {
    private static final Long serialVersionUID = 1L;
    private static final UserBuilder UserBuilder = new UserBuilder();

    private final String id;
    private final String username;
    private final String password;

    private User(@JsonProperty("id") final String id,
                 @JsonProperty("name") final String name,
                 @JsonProperty("password") final String password) {
        super();
        this.id = requireNonNull(id);
        this.username = requireNonNull(name);
        this.password = requireNonNull(password);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserBuilder builder() {
        return UserBuilder;
    }

    public String getId() {
        return id;
    }

    public static class UserBuilder {
        private String id;
        private String username;
        private String password;

        public UserBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(id, username, password);
        }
    }
}
