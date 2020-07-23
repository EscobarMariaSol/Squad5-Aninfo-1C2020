package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.AsignacionProyectoController;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.Empleado;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostrarProyectosDeUnEmpleadoStepDefinitions {

    @Autowired
    private AsignacionProyectoController asignacionProyectoController;
    private ResponseEntity response;



    @Cuando("consulto los proyectos de este empleado cuyo id es {string}")
    public void consulto_los_proyectos_de_este_empleado_cuyo_id_es(String legajoEmpleado) {
        response = asignacionProyectoController.obtenerProyectosAsignados(legajoEmpleado);
    }

    @Entonces("obtengo un listado proyectos asignados a este empleado")
    public void obtengo_un_listado_de_los_proyectos_asignados_a_este_empleado() {
        Set<AsignacionProyecto> proyectos = (Set<AsignacionProyecto>) response.getBody();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(proyectos.size(), 2);
    }
    @Entonces("obtengo un mensaje de que el empleado no existe")
    public void obtengo_un_mensaje_de_que_el_empleado_no_existe(){
        Assert.assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

}
