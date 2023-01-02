package com.blogappapis.mapper;

import com.blogappapis.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserMapper {
    private int id;

    @NotEmpty(message = "User name can not be empty ")
    private String name;

    @Email(message = "email is not valid")
    private String email;

    @NotEmpty
    private String password;

    private String about;

    private Set<Comment> comments = new HashSet<>();
}
