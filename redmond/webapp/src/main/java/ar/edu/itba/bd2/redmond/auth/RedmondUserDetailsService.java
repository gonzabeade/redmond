package ar.edu.itba.bd2.redmond.auth;

import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.exceptions.UserNotFoundException;
import ar.edu.itba.bd2.redmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RedmondUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.getUserByRedmondId(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Redmond ID"));
        return new RedmondUserDetails(
                user.getRedmondId(), user.getPassword(), Collections.emptyList()
        );
    }
}
