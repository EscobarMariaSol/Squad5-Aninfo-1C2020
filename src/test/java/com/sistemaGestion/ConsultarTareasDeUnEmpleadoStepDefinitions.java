package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.AsignacionProyectoController;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.dtos.TareaDTO;
import com.sistemaGestion.model.AsignacionProyecto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ConsultarTareasDeUnEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private AsignacionProyectoController asignacionProyectoController;

    private PerfilEmpleadoDTO perfilEmpleadoDTO;
    private List<TareaDTO> tareasDelEmpleado;
    private ResponseEntity response;

    //Dado
    @Dado("^que existe el empleado$")
    public void que_existe_el_empleado(DataTable empleadoDt) throws Throwable {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    // Cuando
    @Cuando("consulto las tareas que el empleado con legajo {string} tiene asignadas")
    public void consultoLasTareasQueElEmpleadoConLegajoTieneAsignadas(String legajo) {
        response = asignacionProyectoController.obtenerTareasDeUnEmpleado(legajo);
    }

    // Entonces
    @Entonces("obtengo una lista de tareas")
    public void obtengoUnaListaDeTareas() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), tareasDelEmpleado);
    }

}
