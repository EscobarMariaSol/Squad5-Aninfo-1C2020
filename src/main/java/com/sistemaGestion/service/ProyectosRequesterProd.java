package com.sistemaGestion.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemaGestion.exceptions.CargaDeHorasException;
import com.sistemaGestion.model.TareaAsignada;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@Profile({"prod", "local"})
public class ProyectosRequesterProd extends ProyectosRequester{

    @Value("${proyectos.uri}")
    private String uri;

    @Override
    public Boolean empleadoTieneAsignadaLaTarea(String legajo, String tareaId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(uri + "/responsables/" + legajo + "/tareas", String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new CargaDeHorasException("Asegurese de haber ingresado bien los datos");
        }
        ObjectMapper mapper = new ObjectMapper();
        TareaAsignada[] tareaAsignadas = mapper.readValue(response.getBody(), TareaAsignada[].class);
        return Stream.of(tareaAsignadas).anyMatch(tareaAsignada -> tareaAsignada.getId().equals(tareaId));    }
}
