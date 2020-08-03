package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasController;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
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
import java.util.List;

public class MostrarHorasPorTareaStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CargaDeHorasRepository cargaDeHorasRepository;

     @Autowired
     private CargaDeHorasController cargaDeHorasController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;

    @Dado("^que soy el lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() {
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Dado("el empleado con legajo {string} cargo {float} horas en la tarea {string}, del proyecto {string}, el dia {string}")
    public void el_empleado_con_legajo_cargo_horas_en_la_tarea_del_proyecto_el_dia(
            String legajo, Float horas, String tareaId, String  proyectoId, String fecha) {
        // Write code here that turns the phrase above into concrete actions
        Empleado empleado = empleadoRepository.findByLegajo(legajo).orElse(null);
        ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                Actividad.TAREA, Long.parseLong(tareaId), Long.parseLong(proyectoId), LocalDate.parse(fecha), horas);
        response = cargaDeHorasController.cargarHorasDeEmpleado(legajo, reporte);

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string} del proyecto {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto(String legajo, String tareaId, String proyectoId) {
        // Write code here that turns the phrase above into concrete actions
        response = cargaDeHorasController.mostrarHorasEnUnaTarea(legajo, Long.parseLong(tareaId), proyectoId, null);
    }

    @Entonces("se me devuelve un listado con las horas trabajadas por el empleado con legajo {string}, en la tarea {string}, del proyecto {string}.")
    public void se_me_devuelve_un_listado_con_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto(
            String legajo, String tareaId, String proyectoId) {
        // Write code here that turns the phrase above into concrete actions
        List<HorasCargadas> horasCargadasList = (List<HorasCargadas>) response.getBody();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Float.valueOf(8), horasCargadasList.get(0).getHoras());
        Assert.assertEquals(Float.valueOf(3), horasCargadasList.get(1).getHoras());
        Assert.assertEquals(Float.valueOf(5), horasCargadasList.get(2).getHoras());
    }

    @Entonces("se me informa que no puedo realizar dicha accion ya que el emplado con legajo {string} no existe.")
    public void se_me_informa_que_no_puedo_realizar_dicha_accion_ya_que_el_emplado_con_legajo_no_existe(String string) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Y("el empleado con legajo {string} no cargo horas en la tarea {string}, del proyecto {string}")
    public void el_empleado_con_legajo_no_cargo_horas_en_la_tarea_del_proyecto(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me devuelve un mensaje indicando que no hay horas cargadas por el empleado con legajo {string}, en la tares {int}, del proyecto {string}.")
    public void se_me_devuelve_un_mensaje_indicando_que_no_hay_horas_cargadas_por_el_empleado_con_legajo_en_la_tares_del_proyecto(String string, Integer int1, String string2) {
        // Write code here that turns the phrase above into concrete actions
        List<HorasCargadas> horasCargadasList = (List<HorasCargadas>) response.getBody();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(horasCargadasList.isEmpty());
    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string}, del proyecto {string}, el dia {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto_el_dia(String legajo, String tareaId, String proyectoId, String fecha) {
        // Write code here that turns the phrase above into concrete actions
        response = cargaDeHorasController.mostrarHorasEnUnaTarea(legajo, Long.parseLong(tareaId), proyectoId, fecha);
    }

    @Entonces("se me devuelve un listado con las horas trabajadas por el empleado con legajo {string}, en la tarea {string}, del proyecto {string}, el dia {string}.")
    public void se_me_devuelve_un_listado_con_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto_el_dia(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        List<HorasCargadas> horasCargadasList = (List<HorasCargadas>) response.getBody();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Float.valueOf(8), horasCargadasList.get(0).getHoras());
    }

    @After
    public void tearDown() {

        empleadoRepository.deleteAll();
        cargaDeHorasRepository.deleteAll();
    }

}
