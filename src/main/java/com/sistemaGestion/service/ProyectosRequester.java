package com.sistemaGestion.service;

import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

public abstract class ProyectosRequester {

    RestTemplate restTemplate;

    public ProyectosRequester(){
        restTemplate = new RestTemplate();
    }

    public abstract Boolean empleadoTieneAsignadaLaTarea(String legajo, String tareaId) throws IOException;
}
