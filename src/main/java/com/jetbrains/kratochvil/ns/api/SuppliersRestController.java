package com.jetbrains.kratochvil.ns.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class SuppliersRestController {

    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public List<Supplier> listAll() {
        return Arrays.asList(
                new Supplier("1", "Alza.cz"),
                new Supplier("2", "Another Supplier")

        );
    }

    @RequestMapping(value = "/suppliers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Supplier> getDetail(@PathVariable String id) {
        final Supplier supplier;
        switch (id) {
            case "1":
                supplier = new Supplier("1", "Alza.cz");
                break;
            case "2":
                supplier = new Supplier("2", "Another Supplier");
                break;
            default:
                supplier = null;
        }


        return Optional
                .ofNullable(supplier)
                .map(user -> ResponseEntity.ok().body(supplier))      //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  //404 Not found
    }
}