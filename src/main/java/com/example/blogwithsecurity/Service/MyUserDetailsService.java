package com.example.blogwithsecurity.Service;

import com.example.blogwithsecurity.API.ApiException;
import com.example.blogwithsecurity.Model.User;
import com.example.blogwithsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

   @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user=authRepository.findUserByUsername(username);
        if (user==null)throw new ApiException("Wrong username or password");
        return user;
    }
}
