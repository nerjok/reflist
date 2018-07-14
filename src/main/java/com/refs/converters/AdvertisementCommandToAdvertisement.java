package com.refs.converters;


import com.refs.commands.AdvertisementCommand;
import com.refs.models.Advertisement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementCommandToAdvertisement implements Converter<AdvertisementCommand, Advertisement> {


    @Override
    public Advertisement convert(AdvertisementCommand source) {

        final Advertisement advertisement = new Advertisement();

        advertisement.setId(source.getId());
        advertisement.setTitle(source.getTitle());
        advertisement.setDescription(source.getDescription());
        advertisement.setBubu(source.getBubu());


        return advertisement;
    }
}
