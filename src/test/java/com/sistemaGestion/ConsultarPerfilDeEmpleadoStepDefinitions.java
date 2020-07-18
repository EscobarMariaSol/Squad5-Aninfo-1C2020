package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
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
    private EmpleadoController empleadoController;

    private PerfilEmpleadoDTO perfilEmpleadoDTO;
    private ResponseEntity response;

    // Y
    @Y("^existe el empleado con los atributos$")
    public void existeElEmpleadoConLosAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
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
        Assert.assertEquals(perfilEmpleadoDTO, response.getBody());
    }

    @Entonces("obtengo un mensaje indicando que el empleado con legajo {string} no pudo ser encontrado")
    public void obtengoUnMensajeIndicandoQueElEmpleadoConLegajoNoPudoSerEncontrado(String legajo) {
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals(
                "El empleado con legajo " + legajo + " no existe.",
                response.getBody()
        );
    }
    
}
