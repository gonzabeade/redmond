package ar.edu.itba.bd2.redmond.auth;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedmondUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        if(!jwtUtils.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String redmondId = jwtUtils.getRedmondId(token);
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(redmondId);
        } catch (UsernameNotFoundException e) {
            filterChain.doFilter(request, response);
            return;
        }

        if(jwtUtils.isRefreshToken(token)) {
            User redmondUser = userService.getUserByRedmondId(redmondId).orElse(null);
            if(redmondUser == null) {
                filterChain.doFilter(request, response);
                return;
            }

            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtUtils.generateToken(redmondId));
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        authentication.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
