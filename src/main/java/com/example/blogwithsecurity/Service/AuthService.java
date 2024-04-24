package com.example.blogwithsecurity.Service;

import com.example.blogwithsecurity.API.ApiException;
import com.example.blogwithsecurity.Model.User;
import com.example.blogwithsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthRepository authRepository;

    public User getUser(String username){
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        return u;
    }
    public void register(User user) {
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public void updateUser(String username,User user){
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);
    }

    public void deleteUser(String username){
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        authRepository.delete(u);
    }


}
