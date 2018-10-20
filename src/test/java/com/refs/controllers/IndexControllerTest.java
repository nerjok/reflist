package com.refs.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
       // indexController = new IndexController();

        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getIndexPage() {
/*
        String viewName = indexController.getIndexPage(model);

        assertEquals("index", viewName);
        verify(model, times(1)).addAttribute(eq("advertisements2"), anySet());
        //verify(indexController, times(1)).getIndexPage();
        */
    }
}