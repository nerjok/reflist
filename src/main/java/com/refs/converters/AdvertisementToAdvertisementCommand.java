package com.refs.converters;

import com.refs.commands.AdvertisementCommand;
import com.refs.models.Advertisement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementToAdvertisementCommand implements Converter<Advertisement, AdvertisementCommand> {

    @Override
    public AdvertisementCommand convert(Advertisement source) {

        final AdvertisementCommand advertisementCommand = new AdvertisementCommand();

        advertisementCommand.setId(source.getId());
        advertisementCommand.setBubu(source.getBubu());
        advertisementCommand.setDescription(source.getDescription());
        advertisementCommand.setTitle(source.getTitle());
        advertisementCommand.setUrl(source.getUrl());

        return advertisementCommand;
    }
}
