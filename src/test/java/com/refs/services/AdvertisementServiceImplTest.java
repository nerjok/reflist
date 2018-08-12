package com.refs.services;

import com.refs.converters.AdvertisementCommandToAdvertisement;
import com.refs.converters.AdvertisementToAdvertisementCommand;
import com.refs.models.Advertisement;
import com.refs.repository.AdvertisementRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}