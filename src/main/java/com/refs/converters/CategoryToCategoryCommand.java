package com.refs.converters;

import com.refs.commands.AdvertisementCommand;
import com.refs.commands.CategoryCommand;
import com.refs.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category source) {

        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setName(source.getName());
        categoryCommand.setDescription(source.getDescription());
        categoryCommand.setData(source.getData());
        categoryCommand.setAvailability(source.getAvailability());
        return categoryCommand;
    }
}
