package com.blogappapis.controller;

import com.blogappapis.mapper.ApiResponse;
import com.blogappapis.mapper.PostMapper;
import com.blogappapis.mapper.PostResponse;
import com.blogappapis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostMapper> createPost(@RequestBody PostMapper postMapper, @PathVariable Integer userId,
                                                 @PathVariable Integer categoryId){
        PostMapper post = this.postService.createPost(postMapper, userId, categoryId);
        return new ResponseEntity<PostMapper>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostMapper>> getPostByUser(@PathVariable Integer userId){
        List<PostMapper> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostMapper>>(posts, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostMapper>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostMapper> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostMapper>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ){
        PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<PostMapper> getPostById(@PathVariable Integer postId){
        PostMapper Post = this.postService.getPostById(postId);
        return new ResponseEntity<PostMapper>(Post, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostMapper> updatePost(@RequestBody PostMapper postMapper, @PathVariable Integer postId){
        PostMapper updatePost = this.postService.updatePost(postMapper, postId);
        return new ResponseEntity<PostMapper>(updatePost,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostMapper>> searchPost(@PathVariable String keyword){
        List<PostMapper> searchPost = this.postService.searchPost(keyword);
        return new ResponseEntity<List<PostMapper>>(searchPost,HttpStatus.OK);
    }
}
