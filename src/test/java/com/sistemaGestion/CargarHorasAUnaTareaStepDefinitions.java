package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.Tarea;
import com.sistemaGestion.model.TareaId;
import com.sistemaGestion.repository.EmpleadoRepository;
import com.sistemaGestion.repository.TareaRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class CargarHorasAUnaTareaStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private TareaRepository tareaRepository;


    private Empleado  empleado;
    private ResponseEntity response;
    private HorasCargadas horasCargadas;
    private TareaId tareaId;

    @Dado("que soy un desarrollador con legajo {string}")
    public void que_soy_un_desarrollador(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = EmpleadoFactory.crearEmpleado(legajo);
        empleadoRepository.save(empleado);
    }

    @Cuando("cargo {int} horas trabajadas en el dia {string} a una tarea cuyo id es {string} del proyecto con id {string}")
    public void cargo_horas_a_una_tarea(int horas, String fecha, String idTarea, String idProyecto) {
        // Write code here that turns the phrase above into concrete actions
        horasCargadas = new HorasCargadas(fecha, horas);
        tareaId = new TareaId(idTarea, idProyecto);
        response = empleadoController.cargarHorasDeEmpleadoEnUnaTarea(empleado.getLegajo(), idProyecto, idTarea, horasCargadas);
        empleado = empleadoRepository.findByLegajo(empleado.getLegajo()).orElse(null);
    }

    @Entonces("las horas son registradas correctamente")
    public void las_horas_se_registran_correctamente() {
        // Write code here that turns the phrase above into concrete actions
        Empleado empleadoActualizado = (Empleado) response.getBody();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(empleado, empleadoActualizado);
    }

}
