package com.refs.services;

import com.refs.commands.AdvertisementCommand;
import com.refs.converters.AdvertisementCommandToAdvertisement;
import com.refs.converters.AdvertisementToAdvertisementCommand;
import com.refs.exceptions.NotFoundException;
import com.refs.models.Advertisement;
import com.refs.models.User;
import com.refs.repository.AdvertisementRepository;
import com.refs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRepository userRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository,
                                    AdvertisementCommandToAdvertisement advertisementCommandToAdvertisement,
                                    AdvertisementToAdvertisementCommand advertisementToAdvertisementCommand,
                                    UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementCommandToAdvertisement = advertisementCommandToAdvertisement;
        this.advertisementToAdvertisementCommand = advertisementToAdvertisementCommand;
        this.userRepository = userRepository;
    }

    @Override
    public Set<Advertisement> getAdvertisements() {


        log.debug("geting all the advertisements");

        Set<Advertisement> advertisementSet = new HashSet<>();

        advertisementRepository.findAll().iterator().forEachRemaining(advertisementSet::add);
        return advertisementSet;
    }

    @Override
    public Set<Advertisement> getMyAdvertisements() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }


        User user = userRepository.findByUsername(username);
        log.debug(user.getLastName());
        System.out.println(user.getAdvertisement().size());
        System.out.println(user.getId());

        Set<Advertisement> advertisementSet = new HashSet<>();

        //advertisementRepository.findAll("user_id" = user.getId())
        user.getAdvertisement().iterator().forEachRemaining(advertisementSet::add);

        return advertisementSet;
    }

    @Override
    public Advertisement findById(Long id) {
        log.debug("dsfsdf");

        Optional<Advertisement> advertisement = advertisementRepository.findById(id);
/*
        if (!advertisement.isPresent()) {
            throw new RuntimeException("Advertisement Not Found!");
        }
        */
        if (!advertisement.isPresent()) {
            //throw new RuntimeException("Recipe Not Found!");
            throw new NotFoundException("Advertisement Not Found for id value: " + id.toString());
        }

        return advertisement.get();
    }

    @Override
    @Transactional
    public AdvertisementCommand findCommandById(Long id) {
        return advertisementToAdvertisementCommand.convert(findById(id));
    }

    @Override
    public AdvertisementCommand saveAdvertisementCommand(AdvertisementCommand command) {
        log.debug("ASIMPL0");
        Advertisement detachedAdvertisement = advertisementCommandToAdvertisement.convert(command);
        log.debug("ASIMPL1");
        Advertisement savedAdvertisement = advertisementRepository.save(detachedAdvertisement);
        log.debug("ASIMPL2");
        return advertisementToAdvertisementCommand.convert(savedAdvertisement);
    }

    @Override
    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }
}
