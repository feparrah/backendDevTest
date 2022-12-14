package com.backendtest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backendtest.exception.NotFoundException;
import com.backendtest.service.impl.ProductServiceImpl;
import com.backendtest.util.Generator;

@SpringBootTest
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	public static final String URL = "http://localhost:5000";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private ProductServiceImpl service;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getSimilarProducts_SUCCESS() throws Exception {
        when(this.service.getSimilarProductsById("1"))
                .thenReturn(List.of(Generator.genProduct()));

        final MvcResult mvcResult = this.mvc.perform(get(URL + "/product/1/similar"))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void getSimilarProducts_NOT_FOUND() throws Exception {
    	when(this.service.getSimilarProductsById("7"))
        .thenThrow(NotFoundException.class);

        this.mvc.perform(get(URL + "/product/7/similar"))
                .andExpect(status().isNotFound());
    }

}
