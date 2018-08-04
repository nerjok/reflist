package com.refs.services;

import com.refs.commands.CategoryCommand;
import com.refs.converters.CategoryCommandToCategory;
import com.refs.converters.CategoryToCategoryCommand;
import com.refs.models.Advertisement;
import com.refs.models.Category;
import com.refs.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryToCategoryCommand categoryToCategoryCommand;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryCommandToCategory categoryCommandToCategory, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<Category> getCategories() {

        Set<Category> categories = new HashSet<>();


        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public Category findById(Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new RuntimeException("category Not Found!");
        }

        return category.get();
    }

    @Override
    public CategoryCommand saveCategoryCommand(CategoryCommand command) {
        //Advertisement detachedAdvertisement = advertisementCommandToAdvertisement,convert(command);
        Category detachedCategory = categoryCommandToCategory.convert(command);

        Category savedCategory = categoryRepository.save(detachedCategory);

        return categoryToCategoryCommand.convert(savedCategory);
    }

    @Override
    public CategoryCommand findCommandById(Long id) {
        return categoryToCategoryCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Set<Category> getCategoryList() {
        return null;
    }
}
