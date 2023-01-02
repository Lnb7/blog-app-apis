package com.blogappapis.controller;

import com.blogappapis.mapper.ApiResponse;
import com.blogappapis.mapper.CommentMapper;
import com.blogappapis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentMapper> createComment(@RequestBody CommentMapper commentMapper,
                                                       @PathVariable Integer postId,@PathVariable Integer userId){
        CommentMapper comment = this.commentService.createComment(commentMapper, postId, userId);
        return new ResponseEntity<CommentMapper>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>
                (new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
