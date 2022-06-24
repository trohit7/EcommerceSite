package com.example.btechproject.repository;

import com.example.btechproject.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository <Category,Integer>{
    Category findByCategoryName(String categoryName);
}
