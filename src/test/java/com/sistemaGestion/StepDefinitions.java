package com.sistemaGestion;



import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import com.sistemaGestion.model.Empleado;
import org.junit.Assert;

public class StepDefinitions {
    @Dado("^que soy un lider de proyecto$")
    public void soy_lider_de_proyecto() throws Throwable {
        Empleado empleado = new Empleado();
        empleado.es_lider_de_proyecto();
    }

    @Cuando("^agrego una tarea al proyecto$")
    public void agrego_una_tarea() throws Exception {
    }

    @Entonces("^puedo ver la tarea en el proyecto$")
    public void ver_tarea_en_proyecto() throws Exception {
    }

    @Dado("que soy un líder de RRHH")
    public void que_soy_un_líder_de_RRHH() throws Throwable {
        Empleado liderRRHH = new Empleado();
        liderRRHH.es_lider_de_RRHH();
    }

    @Cuando("asigno la senority de un empleado")
    public void asigno_la_senority_de_un_empleado() {
        Empleado empleado = new Empleado();
        empleado.setSeniority("Senior");
        empleado.tieneSeniorityAsignada();
    }

    @Entonces("la seniority especificada se agrega a la información del empleado")
    public void la_seniority_especificada_se_agrega_a_la_información_del_empleado() {
        Empleado empleado = new Empleado();
        empleado.setSeniority("Senior");
        Assert.assertNotNull(empleado.getSeniority());
    }
}
