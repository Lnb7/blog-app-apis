package com.blogappapis.service.Impl;

import com.blogappapis.entity.Comment;
import com.blogappapis.entity.Post;
import com.blogappapis.entity.User;
import com.blogappapis.exception.ResourceNotFoundException;
import com.blogappapis.mapper.CommentMapper;
import com.blogappapis.repositories.CommentRepository;
import com.blogappapis.repositories.PostRepository;
import com.blogappapis.repositories.UserRepository;
import com.blogappapis.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentMapper createComment(CommentMapper commentMapper, Integer postId, Integer userId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));

        Comment comment = this.modelMapper.map(commentMapper, Comment.class);

        comment.setPost(post);
        comment.setUser(user);

        Comment saveComment = this.commentRepository.save(comment);

        return this.modelMapper.map(saveComment, CommentMapper.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "comment id", commentId));
        this.commentRepository.delete(comment);
    }
}
