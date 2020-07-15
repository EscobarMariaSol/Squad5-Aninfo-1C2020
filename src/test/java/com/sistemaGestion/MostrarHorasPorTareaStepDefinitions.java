package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Seniority;
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

public class MostrarHorasPorTareaStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;

    @Dado("^que soy el lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() {
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }


    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string} del proyecto {string}, para la fecha {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto_para_la_fecha(String legajo,
                                                                                                                String idTarea,
                                                                                                                String idProyecto,
                                                                                                                String fecha) {
        // Write code here that turns the phrase above into concrete actions
        //response = empleadoController.consultarHorasEnUnaTarea(legajo, idTarea, idProyecto, fecha);
    }

    @Entonces("obtengo la información de la cantidad de horas trabajadas por el empleado en la tarea indicada durante la fecha indicada.")
    public void obtengo_la_información_de_la_cantidad_de_horas_trabajadas_por_el_empleado_en_la_tarea_indicada_durante_la_fecha_indicada() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string},en la tarea {string} del proyecto {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_la_tarea_del_proyecto(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("obtengo la información de la cantidad de horas trabajadas por el empleado en la tarea indicada.")
    public void obtengo_la_información_de_la_cantidad_de_horas_trabajadas_por_el_empleado_en_la_tarea_indicada() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Y("no hay empleados registrados")
    public void no_hay_empleados_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se me informa que no puedo realizar dicha acción ya que el emplado con legajo {string} no existe.")
    public void se_me_informa_que_no_puedo_realizar_dicha_acción_ya_que_el_emplado_con_legajo_no_existe(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
