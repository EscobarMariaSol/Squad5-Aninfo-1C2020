package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.service.EmpleadoService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class ModificarPerfilDeEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado;
    private ResponseEntity response;

    @Y("existe el  empleado con los atributos")
    public void existeElEmpleadoConLosAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        empleadoController.ingresarEmpleado(empleado);
    }

    @Cuando("cambio el nombre del empleado por {string}")
    public void cambioElNombreDelEmpleadoPorRowena(String nuevoNombre) {
        empleado.setNombre(nuevoNombre);
        response = empleadoController.actualizarEmpleado(empleado);
    }

    @Entonces("el empleado con legajo {string} presenta los siguientes datos")
    public void elEmpleadoConLegajoPresentaLosSiguientesDatos(String legajo, DataTable empleadoDt) {
        Empleado empleadoActualizado = empleadoService.consultarEmpleadoPorLegajo(legajo);

        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        Empleado empleadoEsperado = EmpleadoFactory.crearEmpleado(empleados.get(0));

        Assert.assertEquals(empleadoEsperado, empleadoActualizado);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
