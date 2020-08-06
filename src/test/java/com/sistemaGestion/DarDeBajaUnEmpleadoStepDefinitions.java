package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class DarDeBajaUnEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado;
    private ResponseEntity response;

    //Y
    @Y("^existe el empleado con los atributos:$")
    public void existeElEmpleadoConLosAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        empleadoRepository.save(empleado);
    }

    @Y("no forma parte de ningun proyecto")
    public void noFormaParteDeNingunProyecto() {
        empleado.setProyectosAsignados(new HashSet<>());
    }

    @Y("no tiene tickets asignados")
    public void noTieneTicketsAsignados() {

    }

    @Y("forma parte del proyecto {string}")
    public void formaParteDelProyecto(String codigoProyecto) {
        /*Proyecto proyecto = new Proyecto(codigoProyecto);
        Set<Proyecto> proyectos = new HashSet<>();
        proyectos.add(proyecto);
        empleado.setProyectos(proyectos);
        empleadoRepository.save(empleado);*/
        //TBD cuando creemos proyectos
    }

    @Y("tiene el ticket {string} asignado")
    public void tieneElTicketAsignado(String codigoTicket) {

    }

    @Cuando("doy de baja al empleado con legajo {string}")
    public void doyDeBajaAlEmpleadoConLegajo(String legajo) {
        response = empleadoController.darDeBajaEmpleado(legajo);
    }

    @Entonces("no se incluye al empleado con legajo {string} al consultar por los empleados")
    public void noSeIncluyeAlEmpleadoConLegajoAlConsultarPorLosEmpleados(String legajo) {
        ResponseEntity<List<Empleado>> respuesta = empleadoController.consultarEmpleados();
        List<Empleado> empleados = respuesta.getBody();
        Assert.assertFalse(empleados.contains(empleado));
    }

    @Entonces("recibo un mensaje de que no puedo dar de baja al empleado {string} porque forma parte de un proyecto")
    public void reciboUnMensajeDeQueNoPuedoDarDeBajaAlEmpleadoPorqueFormaParteDeUnProyecto(String legajo) {
        /*Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(
                "No se puede dar de baja al empleado con legajo: "
                        + legajo + " porque forma parte de algún proyecto.",
                response.getBody()
        );*/
        //TBD cuando creemos proyectos, tareas y tickets
    }

    @Y("recibo un mensaje de que no puedo dar de baja al empleado {string} porque tiene un ticket asignado")
    public void reciboUnMensajeDeQueNoPuedoDarDeBajaAlEmpleadoPorqueTieneUnTicketAsignado(String legajo) {
        /*Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(
                "No se puede dar de baja al empleado con legajo: "
                        + legajo + " porque tiene un ticket asignado.",
                response.getBody()
        );*/ //TBD cuando creemos proyectos, tareas y tickets
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
        response = null;
    }

}
