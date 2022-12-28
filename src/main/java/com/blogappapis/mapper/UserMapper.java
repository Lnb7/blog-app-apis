package com.blogappapis.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMapper {
    private int id;

    private String name;

    private String email;

    private String password;

    private String about;
}
