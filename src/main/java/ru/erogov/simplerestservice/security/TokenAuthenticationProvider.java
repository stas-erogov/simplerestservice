package ru.erogov.simplerestservice.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.erogov.simplerestservice.service.UserAuthenticationService;

import java.util.Optional;

@Component
class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserAuthenticationService auth;

    TokenAuthenticationProvider(final UserAuthenticationService auth) {
        this.auth = auth;
    }

    @Override
    protected void additionalAuthenticationChecks(
            final UserDetails userDetails,
            final UsernamePasswordAuthenticationToken token) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(final String username,
                                       final UsernamePasswordAuthenticationToken auth) throws AuthenticationException {
        final Object token = auth.getCredentials();
        return Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(this.auth::findByToken)
                .orElseThrow(()-> new UsernameNotFoundException("Cannot find user with auth-token=" + token));
    }
}
