package com.example.blogwithsecurity.Controller;

import com.example.blogwithsecurity.API.ApiException;
import com.example.blogwithsecurity.API.ApiResponse;
import com.example.blogwithsecurity.Model.User;
import com.example.blogwithsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    @GetMapping("/get")
    public ResponseEntity getUser(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(authService.getUser(user.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("registerd"));
    }

    @PutMapping("/update/{username}")
    public ResponseEntity update(@PathVariable String username,@RequestBody @Valid User user){
        authService.updateUser(username,user);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal User user){
        authService.deleteUser(user.getUsername());
        return ResponseEntity.status(200).body(new ApiResponse("the account deleted"));
    }
}
