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

import java.util.ArrayList;
import java.util.List;

public class AsignarSeniorityStepDefinitions extends SpringIntegrationTest{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;
    private Long idEmpleado;

    @Dado("que soy lider de recursos humanos")
    public void que_soy_lider_de_recursos_humanos() {
        // Write code here that turns the phrase above into concrete actions
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Y("existe un empleado con id {int}")
    public void existe_un_empleado_con_id(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        idEmpleado = Long.valueOf(id);
        empleado = EmpleadoFactory.crearEmpleado(idEmpleado);
        empleadoRepository.save(empleado);
    }

    @Cuando("asigno la seniority {string} a dicho empleado")
    public void asigno_la_seniority_a_dicho_empleado(String seniority) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.asignarSeniority(idEmpleado, seniority);
    }

    @Y("consulto el perfil del empleado con id {int}")
    public void consulto_el_perfil_del_empleado_con_id(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        idEmpleado = Long.valueOf(id);
        response = empleadoController.consultarEmpleadoPorId(idEmpleado);
    }

    @Entonces("obtengo la información del empleado actualizada.")
    public void obtengo_la_información_del_empleado_actualizada() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(empleado, response.getBody());
    }

    @Y("no existe el empleado con id {int}")
    public void no_existe_el_empleado_con_id(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        idEmpleado = Long.valueOf(id);
    }

    @Cuando("quiero asignar la seniority {string} a dicho empleado")
    public void quiero_asignar_la_seniority_a_dicho_empleado(String seniority) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.asignarSeniority(idEmpleado, seniority);
    }

    @Entonces("obtengo un mensaje indicando que el empleado con id {int} no pudo ser encontrado")
    public void obtengo_un_mensaje_indicando_que_el_empleado_con_id_no_pudo_ser_encontrado(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Empleado with id " + idEmpleado + " not found.", response.getBody());
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
