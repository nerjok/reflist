package com.refs.converters;

import com.refs.commands.AdvertisementInfoCommand;
import com.refs.models.Advertisement;
import com.refs.models.AdvertisementInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AdvertisementInfoCommandToAdvertisementInfo implements Converter<AdvertisementInfoCommand, AdvertisementInfo> {


    @Override
    public AdvertisementInfo convert(AdvertisementInfoCommand source) {
        if(source == null) {
            return null;
        }

        final AdvertisementInfo advertisementInfo = new AdvertisementInfo();
        advertisementInfo.setId(source.getId());
        advertisementInfo.setAdvertisementInfo(source.getAdvertisementInfo());
        return advertisementInfo;
    }
}
