package com.jetbrains.kratochvil.ns.api;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SuppliersRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllSuppliers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/suppliers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":\"1\",\"name\":\"Alza.cz\"},{\"id\":\"2\",\"name\":\"Another Supplier\"}]")));
    }

    @Test
    public void getSupplierDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/suppliers/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":\"1\",\"name\":\"Alza.cz\"}")));
    }

    @Test
    public void supplierDetailNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/suppliers/XXX").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
