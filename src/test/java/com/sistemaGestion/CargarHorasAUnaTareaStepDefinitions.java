package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasControler;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CargarHorasAUnaTareaStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CargaDeHorasControler cargaDeHorasController;

    private Empleado  empleado;
    private ResponseEntity response;
    private HorasCargadas horasCargadas;
    private CargaDeHoras cargaDeHoras;

    @Dado("que soy un desarrollador con legajo {string}")
    public void que_soy_un_desarrollador(String legajo) {
        empleado = EmpleadoFactory.crearEmpleado(legajo);
        empleadoRepository.save(empleado);
    }

    @Cuando("cargo {float} horas trabajadas en el dia {string} a una tarea cuyo id es {string} del proyecto con id {string}")
    public void cargo_horas_a_una_tarea(Float horas, String fecha, String idTarea, String idProyecto) {
        horasCargadas = new HorasCargadas(fecha, horas);
        response = cargaDeHorasController.cargarHorasDeEmpleadoEnUnaTarea(empleado.getLegajo(), idProyecto, idTarea, horasCargadas);
    }

    @Entonces("las horas son registradas correctamente")
    public void las_horas_se_registran_correctamente() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
