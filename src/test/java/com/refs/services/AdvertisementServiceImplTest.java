package com.refs.services;

import com.refs.commands.AdvertisementCommand;
import com.refs.converters.AdvertisementCommandToAdvertisement;
import com.refs.converters.AdvertisementToAdvertisementCommand;
import com.refs.exceptions.NotFoundException;
import com.refs.models.Advertisement;
import com.refs.repository.AdvertisementRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AdvertisementServiceImplTest {

    AdvertisementServiceImpl advertisementService;
    @Mock
    AdvertisementRepository advertisementRepository;
    @Mock
    AdvertisementCommandToAdvertisement advertisementCommandToAdvertisement;
    @Mock
    AdvertisementToAdvertisementCommand advertisementToAdvertisementCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        advertisementService = new AdvertisementServiceImpl(advertisementRepository, advertisementCommandToAdvertisement, advertisementToAdvertisementCommand);
    }

    @Test
    public void getAdvertisements() {
        Advertisement advertisement = new Advertisement();
        HashSet<Advertisement> advertisementHashSet = new HashSet<>();
        advertisementHashSet.add(advertisement);

        when(advertisementService.getAdvertisements()).thenReturn(advertisementHashSet);

        Set<Advertisement> advertisements = advertisementService.getAdvertisements();

        assertEquals(advertisements.size(), 1l);
        verify(advertisementRepository, times(1)).findAll();
        //verify(advertisementRepository, times(1)).findAll();

    }


    @Test
    public void findById() throws  Exception{
        Advertisement advertisement = new Advertisement();
        advertisement.setId(1l);

        Optional<Advertisement> advertisementOptional = Optional.of(advertisement);
        when(advertisementRepository.findById(anyLong())).thenReturn(advertisementOptional);

        Advertisement advertisementRetrn = advertisementService.findById(1l);

        assertNotNull("Null advertisement returned", advertisementRetrn);
        verify(advertisementRepository, times(1)).findById(anyLong());
        verify(advertisementRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void findByIdNotFound() throws  Exception{
        Optional<Advertisement> advertisementOptional = Optional.empty();
        when(advertisementRepository.findById(anyLong())).thenReturn(advertisementOptional);

        Advertisement advertisementRetrn = advertisementService.findById(2l);

    }


    @Test
    public void findCommandById() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(1l);
        Optional<Advertisement> advertisementOptional = Optional.of(advertisement);

        when(advertisementRepository.findById(anyLong())).thenReturn(advertisementOptional);

        AdvertisementCommand advertisementCommand = new AdvertisementCommand();
        advertisementCommand.setId(1l);

        when(advertisementToAdvertisementCommand.convert(any())).thenReturn(advertisementCommand);

        AdvertisementCommand advertisementCommandById = advertisementService.findCommandById(1l);

        assertNotNull("Null advertisement returned", advertisementCommandById);
        verify(advertisementRepository, times(1)).findById(anyLong());
        verify(advertisementRepository, never()).findAll();
    }

    @Test
    public void saveAdvertisementCommand() {
    }

    @Test
    public void deleteById() {

        Long idToDelete = new Long(2);

        advertisementService.deleteById(idToDelete);

        verify(advertisementRepository, times(1)).deleteById(anyLong());
    }
}