package com.allianz.healthtourism.util.security;

import com.allianz.healthtourism.database.entity.Role;
import com.allianz.healthtourism.database.entity.User;
import com.allianz.healthtourism.database.repository.RoleRepository;
import com.allianz.healthtourism.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String roleString = "ROLE_";
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not findUser with email =" + email);
        }
        List<Role> roleEntities = new ArrayList<>(user.get().getRoles());
        roleString += roleEntities.get(0).getName();
        System.err.println(roleString);

        return new org.springframework.security.core.userdetails.User(email,
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleString)));
    }
}