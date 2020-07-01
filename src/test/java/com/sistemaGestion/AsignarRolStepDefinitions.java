package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
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

public class AsignarRolStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderDeRecursosHumanos, empleado;
    private ResponseEntity response;
    private Long idEmpleado;

    @Dado("que soy lider de Recursos Humanos y quiero asignar el rol a un empleado")
    public void que_soy_lider_de_Recursos_Humanos_y_quiero_asignar_el_rol_a_un_empleado() {
        // Write code here that turns the phrase above into concrete actions
        liderDeRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Y("existe un empleado cuyo numero de legajo es {string}")
    public void existe_un_empleado_cuyo_numero_de_legajo_es(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = EmpleadoFactory.crearEmpleado(1L);
        empleadoRepository.save(empleado);
    }

    @Cuando("asigno el rol de {string} al empleado con legajo {string}")
    public void asigno_el_rol_de_al_empleado_con_legajo(String rol, String legajo) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.asignarRol(legajo, rol);
    }

    @Entonces("el rol {string} queda registrado en la informacion personal del empleado con legajo {string}.")
    public void el_rol_queda_registrado_en_la_informacion_personal_del_empleado_con_legajo(String rol, String legajo) {
        // Write code here that turns the phrase above into concrete actions
        empleado = empleadoRepository.findOneByLegajo(legajo);
        Assert.assertEquals(empleado.getRol().name(), rol.toUpperCase());
    }

    @Y("no existe un empleado con legajo {string}")
    public void no_existe_un_empleado_con_legajo(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Cuando("selecciono asignar rol {string} al empleado con legajo {string}")
    public void selecciono_asignar_rol_al_empleado_con_legajo(String rol, String legajo) {
        // Write code here that turns the phrase above into concrete actions
        response = empleadoController.asignarRol(legajo, rol);
    }

    @Entonces("se me indica que no existe el empleado con legajo {string}.")
    public void se_me_indica_que_no_existe_el_empleado_con_legajo(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Empleado with legajo " + legajo + " not found.", response.getBody());
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}