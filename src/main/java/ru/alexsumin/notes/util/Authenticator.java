package ru.alexsumin.notes.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alexsumin.notes.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class Authenticator implements UserDetailsService {


    @Autowired
    public UserService service;


    @Autowired
    public Authenticator(UserService service) {
        this.service = service;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        ru.alexsumin.notes.model.User user = service.findByLogin(username);

        if (user == null) throw new UsernameNotFoundException("No user " + username);

        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, user.getEncryptedPassword(), roles);
    }


}
