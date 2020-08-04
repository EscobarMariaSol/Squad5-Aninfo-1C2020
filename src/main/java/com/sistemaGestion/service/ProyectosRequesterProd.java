package com.sistemaGestion.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Profile({"prod", "local"})
public class ProyectosRequesterProd extends ProyectosRequester{

    @Value("${proyectos.uri}")
    private String uri;

    @Override
    public Boolean empleadoTieneAsignadaLaTarea(String proyectoId, String tareaId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(uri + "/proyectos/" + proyectoId + "/tareas/" + tareaId, String.class);
        } catch (HttpClientErrorException e) {
            return false;
        }
        return response.getStatusCode().equals(HttpStatus.OK);

    }
}
