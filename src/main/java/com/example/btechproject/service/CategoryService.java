package com.example.btechproject.service;

import com.example.btechproject.model.Category;
import com.example.btechproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public  void createCategory(Category category){
        categoryRepository.save(category);
    }


    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public  void editCategory(int categoryId, Category updateCategory){
        Category category= categoryRepository.getReferenceById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepository.save(category);
    }

    public  boolean findById(int  categoryId){
        return categoryRepository.findById(categoryId).isPresent();
    }

}
