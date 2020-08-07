package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.AsignacionProyectoController;
import com.sistemaGestion.dtos.AsignacionProyectoDTO;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.repository.EmpleadoRepository;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashSet;

public class AgregarUnEmpleadoAUnProyectoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private AsignacionProyectoRepository asignacionProyectoRepository;

    @Autowired
    private AsignacionProyectoController AsignacionProyectoController;

    private Empleado empleado, liderDeProyecto;
    private ResponseEntity response;
    private AsignacionProyectoDTO asignacionProyecto;

    public AgregarUnEmpleadoAUnProyectoStepDefinitions(AsignacionProyectoRepository asignacionProyectoRepository) {
        this.asignacionProyectoRepository = asignacionProyectoRepository;
    }

    @Dado("que soy lider del proyecto {string} y quiero agregar un empleado a mi proyecto")
    public void que_soy_lider_del_proyecto_y_quiero_agregar_un_empleado_a_mi_proyecto(String codigo) {
        // Write code here that turns the phrase above into concrete actions
        liderDeProyecto = EmpleadoFactory.crearLiderDeProyecto();
    }

    @Y("hay un empleado cuyo legajo es {string}")
    public void hay_un_empleado_cuyo_legajo_es(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = EmpleadoFactory.crearEmpleado(legajo);
        empleado.setProyectosAsignados(new HashSet<>());
        empleado.setActivo(true);
        empleadoRepository.save(empleado);
    }

    @Cuando("agrego al empleado, con lejago {string}, al proyecto {string}")
    public void agrego_al_empleado_con_lejago_al_proyecto(String legajo, String codigo) {
        // Write code here that turns the phrase above into concrete actions
        LocalDate fechaInicio = LocalDate.parse("2020-06-07");
        LocalDate fechaFin = LocalDate.parse("2020-06-16");
        EmpleadoRol rol = EmpleadoRol.DESARROLLADOR;
        asignacionProyecto = new AsignacionProyectoDTO(Long.parseLong(codigo), fechaInicio, fechaFin, rol);
        response = AsignacionProyectoController.asignarEmpleadoAProyecto(legajo, asignacionProyecto);
        Empleado empleado1 = empleadoRepository.findByLegajo(legajo).orElse(null);
    }

    @Entonces("el empleado {string} queda asignado al proyecto {string}.")
    public void el_empleado_queda_asignado_al_proyecto(String legajo, String codigo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = empleadoRepository.findByLegajo(legajo).orElse(null);
        Assert.assertTrue(empleado.getProyectosAsignados().stream()
                .anyMatch(asignacionProyecto ->
                        asignacionProyecto.getCodigoProyecto().equals(Long.parseLong(codigo))
                )
        );
    }

    @Dado("no hay un empleado cuyo legajo es {string}")
    public void no_hay_un_empleado_cuyo_legajo_es(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me informa que no puedo agregar al empleado con legajo {string}, porque no existe.")
    public void se_me_informa_que_no_puedo_agregar_al_empleado_con_legajo_porque_no_existe(String legajo) {
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
        asignacionProyectoRepository.deleteAll();
    }
}
