package com.example.blogwithsecurity.Service;

import com.example.blogwithsecurity.API.ApiException;
import com.example.blogwithsecurity.Model.Blog;
import com.example.blogwithsecurity.Model.User;
import com.example.blogwithsecurity.Repository.AuthRepository;
import com.example.blogwithsecurity.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getUserBlogs(String username) {
        User u = authRepository.findUserByUsername(username);
        if (u == null) {
            throw new ApiException("User not found");
        }
        List<Blog> list=blogRepository.findBlogsByUser(u);
        if (list.isEmpty())throw new ApiException("No Bolgs found");
        return list;
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }


    public Blog getOneBlogById(Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public Blog getOneBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public void addBlog(Integer userId,Blog blog){
        User user=authRepository.findUserById(userId);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId,Integer blogId,Blog blog){
        Blog blog1=blogRepository.findBlogById(blogId);
        if (blog1==null){throw new ApiException("not found blog id");}
        if (!Objects.equals(blog1.getUser().getId(), userId)){throw new ApiException("not match user id ");}
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }
    public void deleteBlog(Integer userId,Integer blogId){
        Blog blog=blogRepository.findBlogById(blogId);
        if (blog==null){throw new ApiException("not found blog");
        }
        if (userId==blog.getUser().getId()){ blogRepository.delete(blog);}
        else throw new ApiException("you are not authorize to delete this blog");

    }


}