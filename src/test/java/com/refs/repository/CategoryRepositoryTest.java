package com.refs.repository;

import com.refs.models.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {


    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFindBy() {

        Category category = new Category();
        category.setId(1l);
        category.setName("Teaspoon");

        Optional<Category> uomOptional = categoryRepository.findById(1l);


        //when(categoryRepository.findById(1l)).thenReturn()

        assertEquals("American9", uomOptional.get().getName());
    }
}