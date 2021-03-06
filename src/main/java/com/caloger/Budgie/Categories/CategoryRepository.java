package com.caloger.Budgie.Categories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Category findByCategoryName(String categoryName);


}
