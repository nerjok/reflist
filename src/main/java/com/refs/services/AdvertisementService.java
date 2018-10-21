package com.refs.services;

import com.refs.commands.AdvertisementCommand;
import com.refs.models.Advertisement;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface AdvertisementService {


    Set<Advertisement> getAdvertisements();

    Set<Advertisement> getMyAdvertisements();

    Advertisement findById(Long id);

    AdvertisementCommand findCommandById(Long id);

    AdvertisementCommand saveAdvertisementCommand(AdvertisementCommand command);

    void deleteById(Long id);

}
