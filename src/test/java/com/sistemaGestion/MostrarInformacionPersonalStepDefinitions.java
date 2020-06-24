package com.sistemaGestion;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class MostrarInformacionPersonalStepDefinitions {

    @Y("hay {int} proyecto registrado")
    public void hay_proyecto_registrado(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("hay {int} empleado registrado en el proyecto")
    public void hay_empleado_registrado_en_el_proyecto(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Cuando("consulto los empleados de dicho proyecto")
    public void consulto_los_empleados_de_dicho_proyecto() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("debo ver un listado con un empleado y la informacion asociada al mismo.")
    public void debo_ver_un_listado_con_un_empleado_y_la_informacion_asociada_al_mismo() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("no hay empleados asignados al proyecto")
    public void no_hay_empleados_asignados_al_proyecto() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("cuentan con {string}")
    public void cuentan_con(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("la consulta {string}")
    public void la_consulta(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

}
