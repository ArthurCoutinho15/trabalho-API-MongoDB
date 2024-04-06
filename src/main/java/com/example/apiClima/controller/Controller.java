package com.example.apiClima.controller;


import com.example.apiClima.model.Entity;
import com.example.apiClima.service.TempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clima")
public class Controller {

    @Autowired
    private TempoService service;
    @GetMapping
    public String preverTempo(){
        return service.preverTempo();
    }

    @PostMapping
    public Entity inserir(@RequestBody Entity user) { return service.inserir(user);}
}