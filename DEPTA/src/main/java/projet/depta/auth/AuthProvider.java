package projet.depta.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import projet.depta.entities.User;
import projet.depta.services.UserServices;

import java.util.Objects;

public class AuthProvider extends DaoAuthenticationProvider {

    @Autowired
    UserServices userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials().toString();
        System.out.println(auth.getCredentials().toString());
        System.out.println(password);
        User user = (User)userDetailsService.loadUserByUsername(name);
        System.out.println(passwordEncoder.matches(password,user.getPassword()));
        System.out.println(passwordEncoder.encode(password));

        if (user == null || !passwordEncoder.matches(password,user.getPassword())) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}