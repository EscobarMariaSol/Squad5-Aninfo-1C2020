package com.sistemaGestion;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class MostrarDiferenciaEntreTiempoRealYEstimadoStepDefinition {

    @Y("hay {int} empleado asignado a un proyecto")
    public void hay_empleado_asignado_a_un_proyecto(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("finalizo una tarea asignada que cuenta con un tiempo estimado para su desarrollo")
    public void finalizo_una_tarea_asignada_que_cuenta_con_un_tiempo_estimado_para_su_desarrollo() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Cuando("consulto diferencia entre tiempo real y tiempo estimado de una tarea asignada a un empleado")
    public void consulto_diferencia_entre_tiempo_real_y_tiempo_estimado_de_una_tarea_asignada_a_un_empleado() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se muestra el valor correspondiente al desvio entre el tiempo real invertido y el estimado.")
    public void se_muestra_el_valor_correspondiente_al_desvio_entre_el_tiempo_real_invertido_y_el_estimado() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("no tiene una tarea asignada")
    public void no_tiene_una_tarea_asignada() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se muestra un mensaje indicando que el empleado no tiene asignada ninguna tarea.")
    public void se_muestra_un_mensaje_indicando_que_el_empleado_no_tiene_asignada_ninguna_tarea() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("tiene una tarea asignada que cuenta con un tiempo estimado para su desarrollo")
    public void tiene_una_tarea_asignada_que_cuenta_con_un_tiempo_estimado_para_su_desarrollo() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("la tarea no ha sido finalizada")
    public void la_tarea_no_ha_sido_finalizada() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se muestra un mensaje indicando que no se puede calcular la diferencia de una tarea que no ha sido finalizada.")
    public void se_muestra_un_mensaje_indicando_que_no_se_puede_calcular_la_diferencia_de_una_tarea_que_no_ha_sido_finalizada() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Cuando("consulto diferencia entre tiempo real y tiempo estimado de un proyecto")
    public void consulto_diferencia_entre_tiempo_real_y_tiempo_estimado_de_un_proyecto() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se muestra el valor de la diferencia, en horas, entre el tiempo real invertido y el tiempo estimado, para el proyecto consultado.")
    public void se_muestra_el_valor_de_la_diferencia_en_horas_entre_el_tiempo_real_invertido_y_el_tiempo_estimado_para_el_proyecto_consultado() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("no hay proyectos registrados")
    public void no_hay_proyectos_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("se muestra un mensaje indicando que no existen proyectos registrados.")
    public void se_muestra_un_mensaje_indicando_que_no_existen_proyectos_registrados() {
        // Write code here that turns the phrase above into concrete actions
    }

}
