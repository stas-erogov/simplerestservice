package ru.erogov.simplerestservice.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String BEARER = "Bearer";

    protected TokenAuthenticationFilter() {
        super(SecurityConfig.PROTECTED_URLS);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final String param = ofNullable(request.getHeader(AUTHORIZATION)).orElse(request.getParameter("t"));

        final String token = ofNullable(param)
                .map(value->removeStart(value, BEARER))
                .map(String::trim)
                .orElseThrow(()->new BadCredentialsException("Missing Authentication Token"));

        final Authentication authentication = new UsernamePasswordAuthenticationToken(token, token);
        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
