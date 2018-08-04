package com.refs.services;

import com.refs.commands.AdvertisementCommand;
import com.refs.commands.CategoryCommand;
import com.refs.models.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();

    Set<Category> getCategoryList();

    Category findById(Long id);

    CategoryCommand saveCategoryCommand(CategoryCommand command);

    CategoryCommand findCommandById(Long id);

    void deleteById(Long id);

}
