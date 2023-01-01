package com.blogappapis.service;

import com.blogappapis.entity.Post;
import com.blogappapis.mapper.PostMapper;
import com.blogappapis.mapper.PostResponse;

import java.util.List;

public interface PostService {

    PostMapper createPost(PostMapper postMapper,Integer userId, Integer categoryId);

    PostMapper updatePost(PostMapper postMapper,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostMapper getPostById(Integer postId);

    List<PostMapper> getPostsByCategory(Integer categoryId);

    List<PostMapper> getPostByUser(Integer userId);

    List<Post> searchPost(String keyword);
}
