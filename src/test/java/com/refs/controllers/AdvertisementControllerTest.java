package com.refs.controllers;

import com.refs.models.Advertisement;
import com.refs.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AdvertisementControllerTest {

    @Mock
    AdvertisementService advertisementService;
    @Mock
    CategoryService categoryService;
    @Mock
    CommentService commentService;
    @Mock
    Model model;
    AdvertisementController advertisementController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        advertisementController = new AdvertisementController(advertisementService, categoryService, commentService);

    }

    @Test
    public void getIndexPage() {

        Set<Advertisement> advertisementHashSet = new HashSet<>();
        advertisementHashSet.add(new Advertisement());

        Advertisement advertisement = new Advertisement();
        advertisement.setId(1l);

        Advertisement advertisement2 = new Advertisement();
        advertisement2.setId(2l);

        advertisementHashSet.add(advertisement);
        advertisementHashSet.add(advertisement2);

        when(advertisementService.getAdvertisements()).thenReturn(advertisementHashSet);

        ArgumentCaptor<Set<Advertisement>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = advertisementController.getIndexPage(model);

        assertEquals("advertisements/index", viewName);
        verify(advertisementService, times(1)).getAdvertisements();
        verify(model, times(1)).addAttribute(eq("advertisements"), argumentCaptor.capture());

        Set<Advertisement> setInController = argumentCaptor.getValue();
        assertEquals(3, setInController.size());
    }

    @Test
    public void tetMockMvc() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();

        mockMvc.perform(get("/advertisement/"))
                .andExpect(status().isOk())
                .andExpect(view().name("advertisements/index"));
    }
    @Test
    public void showById() {
    }

    @Test
    public void newRecipe() {
    }

    @Test
    public void updateRecipe() {
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void saveComment() {
    }

    @Test
    public void handleNotFound() {
    }
}