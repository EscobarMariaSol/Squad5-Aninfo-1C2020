package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ConsultarPerfilDeEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado;
    private ResponseEntity response;

    // Y
    @Y("existe el empleado con legajo {string}")
    public void existeElEmpleadoConLegajo(String arg0) {
        empleado = EmpleadoFactory.crearEmpleado(3L);
        empleadoRepository.save(empleado);
    }

    @Y("no existe el empleado con legajo {string}")
    public void noExisteElEmpleadoConLegajo(String arg0) {
        // No agrego empleados a la base de datos.
    }

    // Cuando
    @Cuando("consulto los datos del empleado con legajo {string}")
    public void consultoLosDatosDelEmpleadoConLegajo(String arg0) {
        response = empleadoController.consultarEmpleado(arg0, null);
    }

    // Entonces
    @Entonces("obtengo los datos del empleado  con legajo {string}")
    public void obtengoLosDatosDelEmpleadoConLegajo(String arg0) {
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(empleado, response.getBody());
    }

    @Entonces("obtengo un mensaje indicando que el empleado con legajo {string} no pudo ser encontrado")
    public void obtengoUnMensajeIndicandoQueElEmpleadoConLegajoNoPudoSerEncontrado(String arg0) {
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Empleado with legajo " + arg0 + " not found.", response.getBody());
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }

}
