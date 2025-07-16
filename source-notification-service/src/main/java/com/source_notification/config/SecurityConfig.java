package com.source_notification.config;

import com.source_notification.security.AuthenticationEntry;
import com.source_notification.security.SecurityConfigurer;
import com.source_notification.security.TokenProvider;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationEntry authenticationEntry;

    private static final List<String> DEFAULT_PERMIT_METHODS = Collections.unmodifiableList(
            Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name()));

    private static final String[] WHITE_LIST = {
            "/resources/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resource/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setConvertToUpperCase(true);

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                .anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntry)
                .and().apply(new SecurityConfigurer(tokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(WHITE_LIST);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(DEFAULT_PERMIT_METHODS);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}
