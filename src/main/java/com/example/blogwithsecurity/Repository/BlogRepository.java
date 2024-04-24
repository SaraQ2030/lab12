package com.example.blogwithsecurity.Repository;

import com.example.blogwithsecurity.Model.Blog;
import com.example.blogwithsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogByTitle(String title);
    Blog findBlogById(Integer id);
    List<Blog> findBlogsByUser(User user);
}
