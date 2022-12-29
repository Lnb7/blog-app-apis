package com.blogappapis.repositories;

import com.blogappapis.entity.Category;
import com.blogappapis.entity.Post;
import com.blogappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
