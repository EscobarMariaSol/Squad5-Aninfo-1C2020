package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Seniority;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AsignarSeniorityStepDefinitions extends SpringIntegrationTest{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;
    private String legajoEmpleado;

    @Dado("que soy Lider de Recursos Humanos")
    public void que_soy_Lider_de_Recursos_Humanos() {
        // Write code here that turns the phrase above into concrete actions
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Cuando("asigno la seniority {string} al empleado con legajo {string}")
    public void asignoLaSeniorityJuniorAlEmpleadoConLegajo(String seniority, String legajo) {
        legajoEmpleado = legajo;
        response = empleadoController.actualizarParcialmenteEmpleado(
                legajo, Seniority.valueOf(seniority.toUpperCase()), null
        );
    }

    @Entonces("al empleado con legajo {string} se le asigna la seniority {string}")
    public void al_empleado_con_legajo_se_le_asigna_la_seniority(String legajo, String seniority) {
        // Write code here that turns the phrase above into concrete actions
        empleado = empleadoRepository.findByLegajo(legajo).orElse(null);
        Assert.assertEquals(empleado.getSeniority(), Seniority.valueOf(seniority.toUpperCase()));
    }

    @Entonces("obtengo un mensaje indicando que el empleado con legajo {string} no fue encontrado")
    public void obtengo_un_mensaje_indicando_que_el_empleado_con_legajo_no_fue_encontrado(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals(
                "El empleado con legajo " + legajo + " no existe.",
                response.getBody()
        );
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
