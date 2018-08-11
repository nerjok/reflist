package com.refs.converters;


import com.refs.commands.AdvertisementCommand;
import com.refs.commands.AdvertisementInfoCommand;
import com.refs.models.Advertisement;
import com.refs.models.Category;
import com.refs.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.refs.converters.CategoryCommandToCategory;
@Component
@Slf4j
public class AdvertisementCommandToAdvertisement implements Converter<AdvertisementCommand, Advertisement> {

    private final CategoryCommandToCategory categoryConveter;
    private final AdvertisementInfoCommandToAdvertisementInfo advertisementInfoConverter;
    private final CategoryService categoryService;


    public AdvertisementCommandToAdvertisement(CategoryCommandToCategory categoryConveter, AdvertisementInfoCommandToAdvertisementInfo advertisementInfoConverter, CategoryService categoryService) {
        this.categoryConveter = categoryConveter;
        this.advertisementInfoConverter = advertisementInfoConverter;
        this.categoryService = categoryService;
    }

    @Override
    public Advertisement convert(AdvertisementCommand source) {
    log.debug("saving data");
        final Advertisement advertisement = new Advertisement();

        advertisement.setId(source.getId());
        advertisement.setTitle(source.getTitle());
        advertisement.setDescription(source.getDescription());
        advertisement.setBubu(source.getBubu());
        advertisement.setUrl(source.getUrl());
        advertisement.setAvailability(source.getAvailability());
        //advertisement.setAdvertisementInfo(source.getAdvertisementInfo());
        advertisement.setAdvertisementInfo(advertisementInfoConverter.convert(source.getAdvertisementInfo()));

        if(source.getCategories() != null && source.getCategories().length > 0) {
          //  source.getCategories()
          //          .forEach( category -> advertisement.getCategories().add(categoryConveter.convert(category)));

            for(int i = 0;i < source.getCategories().length; i++) {

            }

            for(String s :source.getCategories()) {
                log.debug(s);
                Long categoryId =  Long.parseLong(s);

                Category category = categoryService.findById(categoryId);
                advertisement.getCategories().add(category);

            }
        }

        //if (source.getCategories() != null && source.getCategories().size() > 0){
            /*

            source.getCategories()
                    .forEach( category -> advertisement.getCategories().add(categoryConveter.convert(category)));
                    */
        //}
        return advertisement;
    }
}
