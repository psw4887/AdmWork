package com.nhnacademy.service;

import com.nhnacademy.entity.Resident;
import com.nhnacademy.repository.ResidentRepository;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {
    private final ResidentRepository residentRepository;

    public CustomUserDetailsService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident = residentRepository.findByUserId(username)
            .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));

        return new User(resident.getUserId(), resident.getUserPw(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}

