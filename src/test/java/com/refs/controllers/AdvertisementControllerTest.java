package com.refs.controllers;

import com.refs.commands.AdvertisementCommand;
import com.refs.exceptions.NotFoundException;
import com.refs.models.Advertisement;
import com.refs.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        advertisementController = new AdvertisementController(advertisementService, categoryService, commentService);
        mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();

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
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();

        mockMvc.perform(get("/advertisement/"))
                .andExpect(status().isOk())
                .andExpect(view().name("advertisements/index"));
    }


    @Test
    public void showById() throws  Exception {

        Advertisement advertisement = new Advertisement();
        advertisement.setId(8l);

        when(advertisementService.findById(anyLong())).thenReturn(advertisement);

        mockMvc.perform(get("/advertisement/8/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("advertisements/show"))
                .andExpect(model().attributeExists("advertisement"));
    }

    @Test
    public void newRecipe() throws Exception {

        AdvertisementCommand advertisementCommand = new AdvertisementCommand();

        mockMvc.perform(get("/advertisement/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("advertisements/advertisementform"))
                .andExpect(model().attributeExists("advertisement"));
    }

    @Test
    public void updateRecipe() {
    }

    @Test
    public void saveOrUpdate() throws Exception{
        AdvertisementCommand advertisementCommand = new AdvertisementCommand();

        advertisementCommand.setId(9l);
        when(advertisementService.saveAdvertisementCommand(any())).thenReturn(advertisementCommand);
        mockMvc.perform(post("/advertisement")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.param("id", "9")
                .param("bubu", "bubu")
                .param("date", "sdfsd")
                .param("description", "aaad")
                .param("title", "fdssdf")
                .param("url", "fgssd")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/advertisement/9/show"));

    }

    @Test
    public void saveOrUpdateFail() throws Exception{
        AdvertisementCommand advertisementCommand = new AdvertisementCommand();

        advertisementCommand.setId(9l);
        when(advertisementService.saveAdvertisementCommand(any())).thenReturn(advertisementCommand);
        mockMvc.perform(post("/advertisement")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.param("id", "9")
                .param("bubu", "bubu")
                .param("date", "sdfsd")
                .param("description", "aaad")
                .param("title", "fdssdf")
                .param("url", "fg")
        ).andExpect(status().isOk())
                .andExpect(model().attributeExists("advertisement"))
                .andExpect(view().name("advertisements/advertisementform"));

    }

    @Test
    public void deleteById() throws Exception {

        mockMvc.perform(get("/advertisement/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/advertisement"));

        verify(advertisementService ,times(1));

    }

    @Test
    public void saveComment() {
    }

    @Test
    public void handleNotFound() throws  Exception{

        when(advertisementService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/advertisement/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

/*
    @Test
    public void testWrongFormat() throws Exception{
        //when((advertisementService.findById(anyLong()))).thenThrow(NumberFormatException.class);


        mockMvc.perform(get("/advertisement/a/show"))
        //mockMvc.perform(get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
*/
}