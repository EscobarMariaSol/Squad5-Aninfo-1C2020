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

<<<<<<< HEAD
    @Cuando("cargo {float} horas trabajadas en el dia {string} a una tarea cuyo id es {string} del proyecto con id {string}")
    public void cargo_horas_a_una_tarea(Float horas, String fecha, String idTarea, String idProyecto) {
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                Actividad.TAREA, Long.parseLong(idTarea), Long.parseLong(idProyecto), LocalDate.parse(fecha), horas);
=======
    @Cuando("cargo {float} horas trabajadas a una tarea cuyo id es {string} del proyecto con id {string}")
    public void cargo_horas_a_una_tarea(Float horas, String idTarea, String idProyecto) {
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(empleado.getContrato());
        reporte.setActividad(Actividad.TAREA);
        reporte.setFecha(LocalDate.now());
        reporte.setTareaId(idTarea);
        reporte.setProyectoid(idProyecto);
        reporte.setCantidadHoras(horas);
>>>>>>> 6693386a8fc494e6eaa8e9f941180aad35428e1a
        response = cargaDeHorasController.cargarHorasDeEmpleado(empleado.getLegajo(), reporte);
    }

    @Entonces("las horas son registradas correctamente")
    public void las_horas_se_registran_correctamente() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Cuando("cargo {float} horas trabajadas en un mes no vigente a una tarea cuyo id es {string} del proyecto con id {string}")
    public void cargo_horas_a_una_tarea_en_un_mes_no_vigente(Float horas, String idTarea, String idProyecto) {
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(empleado.getContrato());
        reporte.setActividad(Actividad.TAREA);
        reporte.setFecha(LocalDate.parse("2020-02-03"));
        reporte.setTareaId(idTarea);
        reporte.setProyectoid(idProyecto);
        reporte.setCantidadHoras(horas);
        response = cargaDeHorasController.cargarHorasDeEmpleado(empleado.getLegajo(), reporte);
    }
    @Entonces("se me indica que no puedo cargar horas")
    public void se_me_indica_que_no_puedo_cargar_horas() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Cuando("cargo {float} horas correspondientes a vacaciones")
    public void cargo_horas_correspondientes_a_vacaciones(Float horas) {
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(empleado.getContrato());
        reporte.setActividad(Actividad.VACACIONES);
        reporte.setFecha(LocalDate.now());
        reporte.setCantidadHoras(horas);
        response = cargaDeHorasController.cargarHorasDeEmpleado(empleado.getLegajo(), reporte);
    }
}
