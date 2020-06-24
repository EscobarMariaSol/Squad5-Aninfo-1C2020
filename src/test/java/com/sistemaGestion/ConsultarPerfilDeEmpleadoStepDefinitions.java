package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ConsultarPerfilDeEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderRecursosHumanos;
    private List<Empleado> coleccionEmpleados = new ArrayList<>();
    private ResponseEntity response;

    //Dado
    @Dado("^que soy un lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() throws Throwable {
        liderRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    // Y
    @Y("existe el empleado {int}")
    public void existeElEmpleado(int arg0) {

    }

    @Y("no existe el empleado {int}")
    public void noExisteElEmpleado(int arg0) {

    }

    // Cuando
    @Cuando("consulto los datos del empleado {int}")
    public void consultoLosDatosDelEmpleado(int arg0) {

    }

    // Entonces
    @Entonces("obtengo los datos del empleado {int}")
    public void obtengoLosDatosDelEmpleado(int arg0) {

    }

    @Entonces("obtengo un mensaje indicando que el empleado {int} no pudo ser encontrado")
    public void obtengoUnMensajeIndicandoQueElEmpleadoNoPudoSerEncontrado(int arg0) {
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }

}
