package com.blogappapis.mapper;

import com.blogappapis.entity.Category;
import com.blogappapis.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostMapper {

    private int postId;
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryMapper category;

    private UserMapper user;

}
