package com.refs.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
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

    @Test
    public void getData1() {
    }

    @Test
    public void setData() {
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void setDescription() {
    }

    @Test
    public void getAvailability1() {
    }

    @Test
    public void setAvailability() {
    }

    @Test
    public void getId1() {
    }

    @Test
    public void setId() {
    }

    @Test
    public void getName1() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void getAdvertisement1() {
    }

    @Test
    public void setAdvertisement() {
    }
}