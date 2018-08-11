package com.refs.converters;

import com.refs.commands.CategoryCommand;
import com.refs.models.Advertisement;
import com.refs.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand source) {

        if (source == null) {
            return null;
        }

        final Category category = new Category();

        category.setId(source.getId());
        category.setName(source.getName());
        category.setDescription(source.getDescription());
        category.setAvailability(source.getAvailability());
        category.setData(source.getData());
        //category.setAdvertisement(source.getAdvertisements());
        return category;
    }
}
