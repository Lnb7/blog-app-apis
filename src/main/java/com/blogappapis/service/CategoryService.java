package com.blogappapis.service;

import com.blogappapis.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryMapper createCategory(CategoryMapper categoryMapper);

    CategoryMapper updateCategory(CategoryMapper categoryMapper, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryMapper getCategoryById(Integer categoryId);

    List<CategoryMapper> getAllCategory();
}
