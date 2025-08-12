package com.patients.patientsMgt.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.UsersRepository;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Users user = users.get();

        if (!user.getIsActive()) {
            throw new DisabledException("User account is inactive");
        }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
    
}
