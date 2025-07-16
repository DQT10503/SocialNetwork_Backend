package com.source_notification.filter;

import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.Constants;
import com.api.framework.utils.Utilities;
import com.source_notification.security.TokenProvider;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.JWSInputException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthenticationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;

    public AuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        try {
            if (StringUtils.isNotBlank(token)) {
                JWSInput input = new JWSInput(token); // Dùng JWSInput của Keycloak để đọc và giải mã token.
                AccessToken accessToken = input.readJsonContent(AccessToken.class); // Chuyển payload trong token thành một đối tượng AccessToken.
                if (Objects.nonNull(accessToken) && !accessToken.isExpired()) {
                    UserDetails userDetail = new User(accessToken.getPreferredUsername(), "xxx", true, true, true, true,
                            getAuthorities(accessToken.getResourceAccess())); // Tạo một đối tượng UserDetails với: username, password không cần thiết nên để tạm "xxx", getAuthorities(): lấy role của người dùng.
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accessToken, null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    BearerContextHolder.getContext().setMasterAccount(accessToken.getPreferredUsername());
                    BearerContextHolder.getContext().setToken(token);
                    filterChain.doFilter(request, response);
                } else {
                    Utilities.buildErrorResponse(response, HttpStatus.UNAUTHORIZED.value() + "", HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), token);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (JWSInputException e) {
            Utilities.buildErrorResponse(response, HttpStatus.UNAUTHORIZED.value() + "", HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), token);
        } finally {
            BearerContextHolder.clearContext();
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTHORIZATION_HEADER);
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(Constants.BEARER_TOKEN_PREFIX)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, Access> resourceAccess) {
        List<String> roles = new ArrayList<>();
        if (Objects.nonNull(resourceAccess)) {
            for (Map.Entry<String, Access> e : resourceAccess.entrySet()) {
                if (Objects.nonNull(e.getValue().getRoles())) {
                    for (String r : e.getValue().getRoles()) {
                        roles.add(r);
                    }
                }
            }
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }
        return authorities;
    }
}
