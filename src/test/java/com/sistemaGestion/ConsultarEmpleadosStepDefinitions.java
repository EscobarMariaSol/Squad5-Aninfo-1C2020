package com.sistemaGestion;

import com.sistemaGestion.SpringIntegrationTest;
import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.cucumber.java.After;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        liderRecursosHumanos = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    //Y
    @Y("hay empleados con los siguientes atributos$")
    public void hayEmpleadosConLosSiguientesAtributos(DataTable empleadoDt) {
        for (int i=0; i<3; i++) {
            List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
            Empleado empleado = EmpleadoFactory.crearEmpleado(empleados.get(i));
            empleadoRepository.save(empleado);
            coleccionEmpleados.add(empleado);
        }
    }

    @Y("no hay empleados")
    public void noHayEmpleados() {
        coleccionEmpleados = new ArrayList<>();
    }
    //Cuando

    @Cuando("consulto los empleados")
    public void consultoLosEmpleados() {
        response = empleadoController.consultarEmpleados();
    }
    //Entonces

    @Entonces("obtengo un listado de los {int} empleados")
    public void obtengoUnListadoDeLosEmpleados(int arg0) {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(coleccionEmpleados, response.getBody());
    }

    @Entonces("obtengo un listado vacio")
    public void obtengoUnListadoVacio() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(coleccionEmpleados, response.getBody());
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
