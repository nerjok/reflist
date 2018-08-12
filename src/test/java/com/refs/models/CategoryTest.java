package com.refs.models;

import org.junit.Before;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }


    @org.junit.Test
    public void getData() {
    }

    @org.junit.Test
    public void getAvailability() {

    }

    @org.junit.Test
    public void getId() {
        Long idValue = 4l;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @org.junit.Test
    public void getName() {
        String idValue = "kuku";

        category.setName(idValue);

        assertEquals(idValue, category.getName());
    }

    @org.junit.Test
    public void getAdvertisement() {
    }
}