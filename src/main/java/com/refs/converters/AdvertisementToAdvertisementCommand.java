package com.refs.converters;

import com.refs.commands.AdvertisementCommand;
import com.refs.models.Advertisement;
import com.refs.converters.CategoryToCategoryCommand;

import com.refs.models.Category;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class AdvertisementToAdvertisementCommand implements Converter<Advertisement, AdvertisementCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final AdvertisementInfoToAdvertisementInfoCommand advertisementInfoToAdvertisementInfoCommand;

    public AdvertisementToAdvertisementCommand(CategoryToCategoryCommand categoryConverter, AdvertisementInfoToAdvertisementInfoCommand advertisementInfoToAdvertisementInfoCommand) {
        this.categoryConverter = categoryConverter;
        this.advertisementInfoToAdvertisementInfoCommand = advertisementInfoToAdvertisementInfoCommand;
    }

    @Synchronized
    @Nullable
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
            Integer categoriesSize = source.getCategories().size();
            Long [] anArray = new Long[categoriesSize];
            int i = 0;
            for(Category s :source.getCategories()) {
                log.debug(s.getId().toString());
                Long categoryId =  s.getId();
                anArray[i] = categoryId;


                i++;
            }
            advertisementCommand.setCategories(anArray);
            /*
            source.getCategories()
                    .forEach((Category category) -> advertisementCommand.getCategories().add(categoryConverter.convert(category)));
                    */
        }
        return advertisementCommand;
    }
}
