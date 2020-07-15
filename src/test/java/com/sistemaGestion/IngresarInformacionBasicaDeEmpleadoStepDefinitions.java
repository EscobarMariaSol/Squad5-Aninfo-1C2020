package com.sistemaGestion;



import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.es.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public class IngresarInformacionBasicaDeEmpleadoStepDefinitions {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderRecursosHumanos;
    private Empleado empleado;
    private ResponseEntity response;
    private PerfilEmpleadoDTO perfilEmpleadoDTO;

    @Dado("^que soy un lider de proyecto$")
    public void soy_lider_de_proyecto() throws Throwable {
        liderRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Y("^quiero guardar un empledo con  los siguientes atributos$")
    public void existeUnEmpleadoConLosSiguientesAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
    }

    @Cuando("^guardo el nuevo empleado$")
    public void guardo_nuevo_empleado() {
        response = empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    @Entonces("se guarda OK")
    public void se_guarda_ok() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(empleado, response.getBody());
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
