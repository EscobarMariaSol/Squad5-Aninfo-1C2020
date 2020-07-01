package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ConsultarPerfilDeEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado;
    private ResponseEntity response;

    // Y
    @Y("^existe el empleado con los atributos$")
    public void existeElEmpleadoConLosAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        empleadoRepository.save(empleado);
    }

    @Y("no existe el empleado con legajo {string}")
    public void noExisteElEmpleadoConLegajo(String arg0) {
        // No agrego empleados a la base de datos.
    }

    // Cuando

    @Cuando("consulto los datos del empleado con legajo {string}")
    public void consultoLosDatosDelEmpleadoConLegajo(String legajo) {
        response = empleadoController.consultarEmpleado(legajo);
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
