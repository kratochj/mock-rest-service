package com.jetbrains.kratochvil.ns.api;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuppliersRestControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/suppliers");
    }

    @Test
    public void getAllSuppliers() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[{\"id\":\"1\",\"name\":\"Alza.cz\"},{\"id\":\"2\",\"name\":\"Another Supplier\"}]"));
    }
    @Test
    public void getSupplierDetail() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/1",
                String.class);
        assertThat(response.getBody(), equalTo("{\"id\":\"1\",\"name\":\"Alza.cz\"}"));
    }
    @Test
    public void supplierDetailNotFound() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/XXX",
                String.class);
        assertThat(response.getBody(), isEmptyOrNullString());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}


