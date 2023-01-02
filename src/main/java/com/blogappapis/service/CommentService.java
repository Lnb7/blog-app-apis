package com.blogappapis.service;

import com.blogappapis.mapper.CommentMapper;

public interface CommentService {

    CommentMapper createComment(CommentMapper commentMapper,Integer postId,Integer userId);

    void deleteComment(Integer commentId);

}
