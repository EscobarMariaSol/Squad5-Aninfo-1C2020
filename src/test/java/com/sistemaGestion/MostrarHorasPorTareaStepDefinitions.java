package com.sistemaGestion.consultas;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.model.Empleado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.util.ArrayList;

public class MostrarHorasPorTareaStepDefinitions {

    @Dado("^que soy el lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() {
    }

    @Y("hay {int} empleado registrado")
    public void hay_empleado_registrado(Integer int1) {
    }

    @Y("no hay empleados registrados")
    public void no_hay_empleados_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Cuando("realizo una búsqueda de un empleado en el sistema")
    public void realizo_una_búsqueda_de_un_empleado_en_el_sistema() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("selecciono sus tareas asignadas")
    public void selecciono_sus_tareas_asignadas() {
        // Write code here that turns the phrase above into concrete actions
    }


    @Entonces("obtengo la información detallada de la cantidad de horas trabajadas por el empleado en cada tarea.")
    public void obtengo_la_información_detallada_de_la_cantidad_de_horas_trabajadas_por_el_empleado_en_cada_tarea() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("obtengo un mensaje indicandome que no hay empleados registrados")
    public void obtengo_un_mensaje_indicandome_que_no_hay_empleados_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }
}
