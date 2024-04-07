package com.example.apiFipe.service;

import com.example.apiFipe.model.Entity;
import com.example.apiFipe.repository.FipeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FipeService {
    @Autowired
    private FipeRepository fipeRepository;
    public String consultarURL(String apiURL) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
            System.out.println(dados);
        } else {
            dados = "Falha ao obter dados, Código de status " + responseEntity.getStatusCode();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(dados);
            for (JsonNode node : jsonNode) {
                String codigo = node.get("codigo").asText();
                String nome = node.get("nome").asText();



                Entity entity = new Entity();

                entity.setCodigo(codigo);
                entity.setNome(nome);


                inserir(entity);
            }



        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return dados;
    }

    public String consultarMarcas() {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas");
    }

    public String consultarModelos(int marca) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos");
    }

    public String consultarAnos(int marca, int modelo) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" + modelo + "/anos/");
    }

    public String consultarValor(int marca, int modelo, String ano) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" + modelo + "/anos/" + ano);
    }

    public Entity inserir(Entity carro){
        return fipeRepository.save(carro);
    }
}
