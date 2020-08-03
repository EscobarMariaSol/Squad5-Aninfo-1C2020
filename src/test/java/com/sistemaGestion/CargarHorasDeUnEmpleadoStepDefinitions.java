package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasController;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class CargarHorasDeUnEmpleadoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CargaDeHorasController cargaDeHorasController;

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
    public void cargo_horas_a_una_tarea(Float horas, String fecha, Long idTarea, Long idProyecto) {
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                Actividad.TAREA, idTarea, idProyecto, LocalDate.parse(fecha), horas);
        response = cargaDeHorasController.cargarHorasDeEmpleado(empleado.getLegajo(), reporte);
    }

    @Entonces("las horas son registradas correctamente")
    public void las_horas_se_registran_correctamente() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
