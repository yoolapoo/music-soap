package com.epsi.music.security;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.epsi.music.security.SecurityConstants.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Value("${spring.security.authentication.jwt.publickey}")
    private String publicKey;

    @Value("${spring.security.authentication.jwt.expiration:21600}")
    private long delayExpirationToken;*/

    private final CorsAllowedHosts corsAllowedHosts;

    @Autowired
    public SecurityConfig(CorsAllowedHosts corsAllowedHosts) {
        this.corsAllowedHosts = corsAllowedHosts;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
//            .antMatchers(POST, LOGIN_URL).permitAll()
//            .antMatchers(POST, GET_TOKEN_URL).permitAll()
            .antMatchers(GET, HEALTH_URL).permitAll()
            .antMatchers(SWAGGER_URL).permitAll()
            .antMatchers(SWAGGER_UI_URL).permitAll()
            .antMatchers(SWAGGER_RESSOURCES).permitAll()
            .antMatchers(WEBJARS).permitAll()
/*            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), publicKey, delayExpirationToken))*/;
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(corsAllowedHosts.getAllowedHosts());
        corsConfiguration.setAllowedMethods(ImmutableList.of("POST", "GET"));
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        corsConfiguration.setAllowedHeaders(ImmutableList.of("Authorization", "Content-Type", "locale", "process_id"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
