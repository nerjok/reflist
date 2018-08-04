package com.refs.converters;


import com.refs.commands.AdvertisementCommand;
import com.refs.commands.AdvertisementInfoCommand;
import com.refs.models.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.refs.converters.CategoryCommandToCategory;
@Component
@Slf4j
public class AdvertisementCommandToAdvertisement implements Converter<AdvertisementCommand, Advertisement> {

    private final CategoryCommandToCategory categoryConveter;
    private final AdvertisementInfoCommandToAdvertisementInfo advertisementInfoConverter;


    public AdvertisementCommandToAdvertisement(CategoryCommandToCategory categoryConveter, AdvertisementInfoCommandToAdvertisementInfo advertisementInfoConverter ) {
        this.categoryConveter = categoryConveter;
        this.advertisementInfoConverter = advertisementInfoConverter;
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

        //if (source.getCategories() != null && source.getCategories().size() > 0){
            /*
            source.getCategories()
                    .forEach( category -> advertisement.getCategories().add(categoryConveter.convert(category)));
                    */
        //}
        return advertisement;
    }
}
