package com.pharmedian.service;

import com.pharmedian.entity.UserPrinciple;
import com.pharmedian.entity.Users;
import com.pharmedian.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);

        if (user == null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(user);
    }
}
