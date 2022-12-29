package com.blogappapis.service.Impl;

import com.blogappapis.entity.Category;
import com.blogappapis.exception.ResourceNotFoundException;
import com.blogappapis.mapper.CategoryMapper;
import com.blogappapis.repositories.CategoryRepository;
import com.blogappapis.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryMapper createCategory(CategoryMapper categoryMapper) {
        Category category = this.modelMapper.map(categoryMapper, Category.class);
        Category saveCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(saveCategory,CategoryMapper.class);
    }

    @Override
    public CategoryMapper updateCategory(CategoryMapper categoryMapper, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        category.setCategoryTitle(categoryMapper.getCategoryTitle());
        category.setDescription(categoryMapper.getDescription());

        Category updateCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updateCategory,CategoryMapper.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryMapper getCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(category, CategoryMapper.class);
    }

    @Override
    public List<CategoryMapper> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryMapper> categoryMappers = categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryMapper.class)).collect(Collectors.toList());
        return categoryMappers;
    }
}
