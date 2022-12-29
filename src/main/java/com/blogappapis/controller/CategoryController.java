package com.blogappapis.controller;

import com.blogappapis.mapper.ApiResponse;
import com.blogappapis.mapper.CategoryMapper;
import com.blogappapis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/")
    public ResponseEntity<CategoryMapper> createCategory(@Valid @RequestBody CategoryMapper categoryMapper){
        CategoryMapper category = this.categoryService.createCategory(categoryMapper);
        return new ResponseEntity<CategoryMapper>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryMapper> updateCategory(@Valid @RequestBody CategoryMapper categoryMapper,
                                                         @PathVariable Integer categoryId){
        CategoryMapper category = this.categoryService.updateCategory(categoryMapper,categoryId);
        return new ResponseEntity<CategoryMapper>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryMapper> getCategoryById(@PathVariable Integer categoryId){
        CategoryMapper category = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryMapper>(category,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryMapper>> getAllCategory(){
        List<CategoryMapper> category = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryMapper>>(category,HttpStatus.OK);
    }
}
