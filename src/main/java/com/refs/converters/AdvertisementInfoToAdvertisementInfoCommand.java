package com.refs.converters;

import com.refs.commands.AdvertisementCommand;
import com.refs.commands.AdvertisementInfoCommand;
import com.refs.models.AdvertisementInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AdvertisementInfoToAdvertisementInfoCommand implements Converter<AdvertisementInfo, AdvertisementInfoCommand> {


    @Override
    public AdvertisementInfoCommand convert(AdvertisementInfo source) {

        if (source == null) {
            return null;
        }

        AdvertisementInfoCommand advertisementInfoCommand = new AdvertisementInfoCommand();

        advertisementInfoCommand.setId(source.getId());
        advertisementInfoCommand.setAdvertisementInfo(source.getAdvertisementInfo());


        return advertisementInfoCommand;
    }
}
