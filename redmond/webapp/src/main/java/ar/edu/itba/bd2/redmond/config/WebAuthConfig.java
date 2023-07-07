package ar.edu.itba.bd2.redmond.config;

import ar.edu.itba.bd2.redmond.auth.JwtFilter;
import ar.edu.itba.bd2.redmond.auth.RedmondUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RedmondUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl().disable()
                .and().authorizeRequests()
                    .antMatchers("/hello").permitAll()
                    .antMatchers("/login").anonymous()
                    .antMatchers(HttpMethod.POST, "/users").anonymous()
                    .anyRequest().authenticated()
                .and().exceptionHandling()
                    .accessDeniedHandler((req, res, e) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN))
                    .authenticationEntryPoint((req, res, e) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .csrf().disable()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Collections.singletonList("*"));
        cors.setAllowedMethods(Collections.singletonList("*"));
        cors.setAllowedHeaders(Collections.singletonList("*"));
        cors.setExposedHeaders(Arrays.asList("Authorization", "X-Refresh", "Location", "Link", "X-Total-Pages"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
}
