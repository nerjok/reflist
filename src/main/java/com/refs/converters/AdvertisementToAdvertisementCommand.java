package com.refs.converters;

import com.refs.commands.AdvertisementCommand;
import com.refs.models.Advertisement;
import com.refs.converters.CategoryToCategoryCommand;

import com.refs.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementToAdvertisementCommand implements Converter<Advertisement, AdvertisementCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final AdvertisementInfoToAdvertisementInfoCommand advertisementInfoToAdvertisementInfoCommand;

    public AdvertisementToAdvertisementCommand(CategoryToCategoryCommand categoryConverter, AdvertisementInfoToAdvertisementInfoCommand advertisementInfoToAdvertisementInfoCommand) {
        this.categoryConverter = categoryConverter;
        this.advertisementInfoToAdvertisementInfoCommand = advertisementInfoToAdvertisementInfoCommand;
    }

    @Override
    public AdvertisementCommand convert(Advertisement source) {
        if (source == null) {
            return null;
        }

        final AdvertisementCommand advertisementCommand = new AdvertisementCommand();

        advertisementCommand.setId(source.getId());
        advertisementCommand.setBubu(source.getBubu());
        advertisementCommand.setDescription(source.getDescription());
        advertisementCommand.setTitle(source.getTitle());
        advertisementCommand.setUrl(source.getUrl());
        advertisementCommand.setAdvertisementInfo(advertisementInfoToAdvertisementInfoCommand.convert(source.getAdvertisementInfo()));
        advertisementCommand.setAvailability(source.getAvailability());
        //command.setNotes(notesConverter.convert(source.getNotes()));
        //advertisementCommand.setAdvertisementInfo(advertisementInfoConverter.convert(source.getAdvertisementInfo()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> advertisementCommand.getCategories().add(categoryConverter.convert(category)));
        }
        return advertisementCommand;
    }
}
