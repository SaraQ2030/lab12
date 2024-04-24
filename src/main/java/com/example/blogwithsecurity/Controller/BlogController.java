package com.example.blogwithsecurity.Controller;

import com.example.blogwithsecurity.API.ApiResponse;
import com.example.blogwithsecurity.Model.Blog;
import com.example.blogwithsecurity.Model.User;
import com.example.blogwithsecurity.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-user-blogs/{username}")
    public ResponseEntity getBlogsByUsername(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getUserBlogs(user.getUsername()));
    }
    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getBlogs());
    }

    @GetMapping("/get-by-id/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId){
        return ResponseEntity.status(200).body(blogService.getOneBlogById(blogId));
    }
    @GetMapping("/get-by-tit/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getOneBlogByTitle(title));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody@Valid Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully "));
    }

    @PutMapping("/update/{blogNum}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody@Valid Blog blog,@PathVariable Integer blogNum){
        blogService.updateBlog(user.getId(),blogNum,blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog  updated successfully "));
    }

    @DeleteMapping("/delete/{num}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer num){
        blogService.deleteBlog(user.getId(),num);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully "));
    }
}
