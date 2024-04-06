package com.example.apiClima.service;

import com.example.apiClima.model.Entity;
import com.example.apiClima.repository.TempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TempoService {

    @Autowired
    private TempoRepository tempoRepository;
    public String preverTempo() {
        String dadosMeteorologicos = "";
        //Nível BR
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";
        //Nível BH
        //String apiUrl = "http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosMeteorologicos = responseEntity.getBody();
        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorológicos. Código de status: " + responseEntity.getStatusCode();
        }


        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(dadosMeteorologicos);
            for (JsonNode node : jsonNode) {
                String country = node.get("country").asText();
                String date = node.get("date").asText();
                String text = node.get("text").asText();


                Entity entity = new Entity();

                entity.setCountry(country);
                entity.setDate(date);
                entity.setText(text);

                inserir(entity);
            }



        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return dadosMeteorologicos;


    }

    public Entity inserir(Entity user) {
        return tempoRepository.save(user);
    }



}