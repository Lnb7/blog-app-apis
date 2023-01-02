package com.blogappapis.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentMapper {
    private int id;

    private String content;
}
