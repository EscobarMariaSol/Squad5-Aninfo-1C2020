package com.sistemaGestion.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test"})
public class ProyectosRequesterLocal extends ProyectosRequester{

    @Override
    public Boolean empleadoTieneAsignadaLaTarea(String legajo, String tareaId) {
        return true;
    }
}
