package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Seniority;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AsignarSeniorityStepDefinitions {

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderRecursosHumanos, desarrollador;

    private Seniority seniority;


    @Dado("que soy lider de recursos humanos")
    public void que_soy_lider_de_recursos_humanos() throws Throwable {
        liderRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Cuando("asigno la seniority de un empleado existente")
    public void asigno_la_seniority_de_un_empleado_existente() {
        desarrollador = EmpleadoFactory.crearDesarrollador();
        seniority = Seniority.Junior;
        empleadoController.asignarSeniority();
    }

    @Entonces("el perfil del empleado indica dicha seniority")
    public void el_perfil_del_empleado_indica_dicha_seniority() {

    }

}
