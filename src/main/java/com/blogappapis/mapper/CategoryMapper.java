package com.blogappapis.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryMapper {

    private int categoryId;

    @NotEmpty(message = "Category title can not be empty")
    private String categoryTitle;

    @NotEmpty(message = "Category description can not be empty")
    private  String description;
}
