package com.example.apiFipe.controller;

import com.example.apiFipe.model.Entity;
import com.example.apiFipe.service.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marcas")
public class Controller {
    @Autowired
    private FipeService service;
    @GetMapping
    public String consultarMarcas(){
        return service.consultarMarcas();
    }
    @PostMapping
    public Entity inserir(@RequestBody Entity carro) { return service.inserir(carro);}
}