package com.sistemaGestion;

import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEmpleadosStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado liderRecursosHumanos;
    private List<Empleado> coleccionEmpleados = new ArrayList<>();
    private ResponseEntity response;

    //Dado
    @Dado("^que soy un lider de recursos humanos$")
    public void soy_lider_de_recursos_humanos() throws Throwable {
        liderRecursosHumanos = new Empleado.Builder().con(empleadoData -> {
            empleadoData.conNombre("lider");
        }).build();
    }

    //Y
    @Y("hay {int} empleados")
    public void hayEmpleados(int arg0) {
        for (int i=0; i<3; i++) {
            int idx = i;
            Empleado empleado = new Empleado.Builder().con(empleadoData -> {
                empleadoData.conNombre("empleado" + idx);
            }).build();
            coleccionEmpleados.add(empleado);
            empleadoRepository.save(empleado);
        }
    }

    //Cuando
    @Cuando("consulto los empleados")
    public void consultoLosEmpleados() {
        response = empleadoController.consultarEmpleados();
    }

    //Entonces
    @Entonces("obtengo un listado de los {int} empleados")
    public void obtengoUnListadoDeLosEmpleados(int arg0) {
        System.out.println(coleccionEmpleados);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(coleccionEmpleados, response.getBody());
    }

}
