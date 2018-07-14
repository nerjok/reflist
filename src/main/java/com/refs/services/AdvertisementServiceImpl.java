package com.refs.services;

import com.refs.commands.AdvertisementCommand;
import com.refs.converters.AdvertisementCommandToAdvertisement;
import com.refs.converters.AdvertisementToAdvertisementCommand;
import com.refs.models.Advertisement;
import com.refs.repository.AdvertisementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementCommandToAdvertisement advertisementCommandToAdvertisement;
    private final AdvertisementToAdvertisementCommand advertisementToAdvertisementCommand;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, AdvertisementCommandToAdvertisement advertisementCommandToAdvertisement, AdvertisementToAdvertisementCommand advertisementToAdvertisementCommand) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementCommandToAdvertisement = advertisementCommandToAdvertisement;
        this.advertisementToAdvertisementCommand = advertisementToAdvertisementCommand;
    }

    @Override
    public Set<Advertisement> getAdvertisements() {


        log.debug("geting all the advertisements");

        Set<Advertisement> advertisementSet = new HashSet<>();

        advertisementRepository.findAll().iterator().forEachRemaining(advertisementSet::add);
        return advertisementSet;
    }

    @Override
    public Advertisement findById(Long id) {
        log.debug("dsfsdf");

        Optional<Advertisement> advertisement = advertisementRepository.findById(id);

        if (!advertisement.isPresent()) {
            throw new RuntimeException("Advertisement Not Found!");
        }

        return advertisement.get();
    }

    @Override
    public AdvertisementCommand findCommandById(Long id) {
        return advertisementToAdvertisementCommand.convert(findById(id));
    }

    @Override
    public AdvertisementCommand saveAdvertisementCommand(AdvertisementCommand command) {

        //Advertisement detachedAdvertisement = advertisementCommandToAdvertisement,convert(command);
        Advertisement detachedAdvertisement = advertisementCommandToAdvertisement.convert(command);

        Advertisement savedAdvertisement = advertisementRepository.save(detachedAdvertisement);

        return advertisementToAdvertisementCommand.convert(savedAdvertisement);
    }

    @Override
    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }
}
