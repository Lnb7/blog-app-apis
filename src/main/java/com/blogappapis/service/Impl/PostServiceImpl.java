package com.blogappapis.service.Impl;

import com.blogappapis.entity.Category;
import com.blogappapis.entity.Post;
import com.blogappapis.entity.User;
import com.blogappapis.exception.ResourceNotFoundException;
import com.blogappapis.mapper.PostMapper;
import com.blogappapis.mapper.PostResponse;
import com.blogappapis.repositories.CategoryRepository;
import com.blogappapis.repositories.PostRepository;
import com.blogappapis.repositories.UserRepository;
import com.blogappapis.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public PostMapper createPost(PostMapper postMapper,Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postMapper, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepository.save(post);
        PostMapper mapper = this.modelMapper.map(newPost, PostMapper.class);

        return mapper;
    }

    @Override
    public PostMapper updatePost(PostMapper postMapper, Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        post.setTitle(postMapper.getTitle());
        post.setContent(postMapper.getContent());
        post.setImageName(postMapper.getImageName());

        Post updatePost = this.postRepository.save(post);
        PostMapper updatedPostMapper = this.modelMapper.map(updatePost, PostMapper.class);
        return updatedPostMapper;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePosts = this.postRepository.findAll(pageRequest);
        List<Post> posts = pagePosts.getContent();
        List<PostMapper> postMapperList = posts.stream().map(post -> this.modelMapper.map(post, PostMapper.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postMapperList);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElement(pagePosts.getTotalElements());
        postResponse.setTotalPage(pagePosts.getTotalPages());
        postResponse.setLastPage(postResponse.isLastPage());

        return postResponse;
    }

    @Override
    public PostMapper getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        PostMapper postMapper = this.modelMapper.map(post, PostMapper.class);
        return postMapper;
    }

    @Override
    public List<PostMapper> getPostsByCategory(Integer categoryId) {
        Category category  = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> posts = this.postRepository.findByCategory(category);

        List<PostMapper> postMappers = posts.stream().map(post -> this.modelMapper.map(post, PostMapper.class))
                .collect(Collectors.toList());

        return postMappers;
    }

    @Override
    public List<PostMapper> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Post> posts = this.postRepository.findByUser(user);

        List<PostMapper> postMappers = posts.stream()
                .map(post -> this.modelMapper.map(post, PostMapper.class)).collect(Collectors.toList());
        return postMappers;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
