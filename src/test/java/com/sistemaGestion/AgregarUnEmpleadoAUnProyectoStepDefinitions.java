package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Proyecto;
import com.sistemaGestion.repository.EmpleadoRepository;
import com.sistemaGestion.repository.ProyectoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AgregarUnEmpleadoAUnProyectoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado, liderDeProyecto;
    private ResponseEntity response;
    private Proyecto proyecto;

    public AgregarUnEmpleadoAUnProyectoStepDefinitions(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Dado("que soy lider del proyecto {string} y quiero agregar un empleado a mi proyecto")
    public void que_soy_lider_del_proyecto_y_quiero_agregar_un_empleado_a_mi_proyecto(String codigo) {
        // Write code here that turns the phrase above into concrete actions
        Proyecto proyecto = new Proyecto(codigo);
        liderDeProyecto = EmpleadoFactory.crearLiderDeProyecto();
    }

    @Y("hay un empleado cuyo legajo es {string}")
    public void hay_un_empleado_cuyo_legajo_es(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = EmpleadoFactory.crearEmpleado(legajo);
        empleadoRepository.save(empleado);
    }

    @Cuando("agrego al empleado, con lejago {string}, al proyecto {string}")
    public void agrego_al_empleado_con_lejago_al_proyecto(String legajo, String codigo) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.agregarAProyecto(legajo, codigo);
    }

    @Entonces("el empleado {string} queda asignado al proyecto {string}.")
    public void el_empleado_queda_asignado_al_proyecto(String legajo, String codigo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = empleadoRepository.findByLegajo(legajo).orElse(null);
        proyecto = proyectoRepository.findByCodigo(codigo).orElse(null);
        Assert.assertTrue(empleado.empleadoPerteneceAProyecto(proyecto));
    }

    @Dado("no hay un empleado cuyo legajo es {string}")
    public void no_hay_un_empleado_cuyo_legajo_es(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me informa que no puedo agregar al empleado con legajo {string}, porque no existe.")
    public void se_me_informa_que_no_puedo_agregar_al_empleado_con_legajo_porque_no_existe(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Empleado with legajo " + legajo + " not found.", response.getBody());
    }

    @Dado("que soy líder de un proyecto que ya ha sido finalizado")
    public void que_soy_líder_de_un_proyecto_que_ya_ha_sido_finalizado() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Entonces("se me informa que no se pueden agregar empleados a un proyecto que ya ha sido finalizado.")
    public void se_me_informa_que_no_se_pueden_agregar_empleados_a_un_proyecto_que_ya_ha_sido_finalizado() {
        // Write code here that turns the phrase above into concrete actions

    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
