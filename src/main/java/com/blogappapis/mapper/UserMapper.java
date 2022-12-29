package com.blogappapis.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
}
