package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.json.HTTP;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ModificarAsignacionDeUnEmpleadoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private AsignacionProyectoRepository asignacionProyectoRepository;

    @Autowired
    private com.sistemaGestion.controller.AsignacionProyectoController AsignacionProyectoController;

    private Empleado empleado, liderDeProyecto;
    private ResponseEntity response;
    private AsignacionProyecto asignacionProyecto;

    @Dado("que soy lider del proyecto {string} y quiero modificar la asignacion de un empleado en el proyecto")
    public void que_soy_lider_del_proyecto_y_quiero_modificar_la_asignacion_de_un_empleado_en_el_proyecto(String string) {
        // Write code here that turns the phrase above into concrete actions
        liderDeProyecto = EmpleadoFactory.crearLiderDeProyecto();
    }

    @Y("existe un empleado con la siguiente informacion")
    public void existe_un_empleado_con_la_siguiente_informacion(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
       List<Map<String, String>> empleados = dataTable.asMaps(String.class, String.class);
        PerfilEmpleadoDTO perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    @Y("el empleado con legajo {string} fue asignado al proyecto {string} en la fecha {string}")
    public void el_empleado_con_legajo_fue_asignado_al_proyecto_en_la_fecha(String legajo, String proyectoId, String fechaInicio) {
        // Write code here that turns the phrase above into concrete actions
        response = AsignacionProyectoController.asignarEmpleadoAProyecto(
                legajo, new AsignacionProyecto(
                        Long.parseLong(proyectoId),
                        LocalDate.parse(fechaInicio),
                        null,
                        EmpleadoRol.DESARROLLADOR)
        );
    }

    @Cuando("modifico la asignación del empleado con legajo {string} en el proyecto {string}, indicando que finalizo en la fecha {string}")
    public void modifico_la_asignación_del_empleado_con_legajo_en_el_proyecto_indicando_que_finalizo_en_la_fecha(String legajo, String proyectoId, String fechaFin) {
        // Write code here that turns the phrase above into concrete actions
        response = AsignacionProyectoController.modificarAsignacionDeEmpleadoAProyecto(
                legajo, Long.parseLong(proyectoId), LocalDate.parse(fechaFin));
    }

    @Entonces("la asignacion del empleado con legajo {string} en el proyecto {string} indica que su fecha de finalizacion es el {string}.")
    public void la_asignacion_del_empleado_con_legajo_en_el_proyecto_indica_que_su_fecha_de_finalizacion_es_el(
            String legajo, String codigoProyecto, String fechaFin) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        AsignacionProyecto asignacionProyecto = (AsignacionProyecto) response.getBody();
        Assert.assertEquals(asignacionProyecto.getFechaFin(), LocalDate.parse(fechaFin));

    }

    @Dado("el empleado con legajo {string} no ha sido asignado al proyecto {string}")
    public void el_empleado_con_legajo_no_ha_sido_asignado_al_proyecto(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me informa que no puedo realizar dicha acción porque el emplado no fue asignado a ese proyecto.")
    public void se_me_informa_que_no_puedo_realizar_dicha_acción_porque_el_emplado_no_fue_asignado_a_ese_proyecto() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me informa que no puedo realizar dicha acción porque el empladono existe.")
    public void se_me_informa_que_no_puedo_realizar_dicha_acción_porque_el_empladono_existe() {
        // Write code here that turns the phrase above into concrete actions

    }

    @After
    public void tearDown() {

        empleadoRepository.deleteAll();
        asignacionProyectoRepository.deleteAll();
    }
}
