package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.Seniority;
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

public class MostrarHorasPorTareaStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private CargaDeHorasRepository cargaDeHorasRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;

    @Dado("^que soy el lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() {
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Y("el empleado con legajo {string} cargo {int} horas en la tarea {string}, del proyecto {string}, el dia {string}")
    public void el_empleado_con_legajo_cargo_horas_en_la_tarea_del_proyecto_el_dia(String legajo, int horas, String idTarea, String idProyecto, String fecha) {
        // Write code here that turns the phrase above into concrete actions
        HorasCargadas horasCargadas = new HorasCargadas(fecha, horas);
        response = empleadoController.cargarHorasDeEmpleadoEnUnaTarea(legajo, idProyecto, idTarea, horasCargadas);
    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string} del proyecto {string}, para la fecha {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto_para_la_fecha(String legajo, String idTarea, String idProyecto, String fecha) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.consultarHorasEnUnaTarea(legajo, idTarea, idProyecto, fecha);

    }

    @Entonces("se me indica que el empleado con legajo {string} trabajo {int} horas en la tarea {string}, del proyecto {string}, para la fecha {string}")
    public void se_me_indica_que_el_empleado_con_legajo_trabajo_horas_en_la_tarea_del_proyecto_para_la_fecha(String legajo, Integer horas, String idTarea, String idProyecto, String fecha) {
        // Write code here that turns the phrase above into concrete actions
        empleado = empleadoRepository.findByLegajo(legajo).orElseThrow( () ->
                new EmpleadoException("Empleado with legajo " + legajo + " not found."));
        HorasCargadas horasTrabajadas = (HorasCargadas) response.getBody();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(horas.intValue(), horasTrabajadas.getHoras());
    }

    @Y("dicho empleado cargo horas en la tarea {string}, del proyecto {string}")
    public void dicho_empleado_cargo_horas_en_la_tarea_del_proyecto(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string} del proyecto {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Y("no hay empleados registrados")
    public void no_hay_empleados_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }


    @Entonces("se me informa que no puedo realizar dicha accion ya que el emplado con legajo {string} no existe.")
    public void se_me_informa_que_no_puedo_realizar_dicha_accion_ya_que_el_emplado_con_legajo_no_existe(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
