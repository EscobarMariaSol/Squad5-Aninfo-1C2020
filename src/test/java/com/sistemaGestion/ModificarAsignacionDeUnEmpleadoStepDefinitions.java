package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.AsignacionProyectoController;
import com.sistemaGestion.controller.CargaDeHorasController;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ModificarAsignacionDeUnEmpleadoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

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

    }

    @Dado("el empleado con legajo {string} fue asignado al proyecto {string} en la fecha {string}")
    public void el_empleado_con_legajo_fue_asignado_al_proyecto_en_la_fecha(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Cuando("modifico la asignación del empleado con legajo {string} en el proyecto {string}, indicando que finalizo en la fecha {string}")
    public void modifico_la_asignación_del_empleado_con_legajo_en_el_proyecto_indicando_que_finalizo_en_la_fecha(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("la asignacion del empleado con legajo {string} en el proyecto {string} indica que su fecha de finalizacion es el {string}.")
    public void la_asignacion_del_empleado_con_legajo_en_el_proyecto_indica_que_su_fecha_de_finalizacion_es_el(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

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
