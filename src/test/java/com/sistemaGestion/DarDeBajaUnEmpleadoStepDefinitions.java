package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Proyecto;
import com.sistemaGestion.model.Tarea;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DarDeBajaUnEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoController empleadoController;

    private Empleado empleado;
    private Set<Proyecto> proyectos;
    private Tarea tarea;
    private ResponseEntity response;

    //Y
    @Y("no tiene tareas asignadas")
    public void noTieneTareasAsignadas(int arg0) {
        empleado.setProyectos(new HashSet<>());
    }

    @Y("no tiene tickets asignados")
    public void noTieneTicketsAsignados() {
    }

    @Y("no forma parte de ningun proyecto")
    public void noFormaParteDeNingunProyecto() {
        empleado.setProyectos(new HashSet<>());
    }

    @Cuando("doy de baja al empleado con legajo {string}")
    public void doyDeBajaAlEmpleadoConLegajo(String legajo) {
        empleadoController.darDeBajaEmpleado(legajo);
    }

    @Entonces("no se incluye al empleado con legajo {string} al consultar por los empleados")
    public void noSeIncluyeAlEmpleadoConLegajoAlConsultarPorLosEmpleados(int arg0) {
        ResponseEntity<List<Empleado>> respuesta = empleadoController.consultarEmpleados();
        List<Empleado> empleados = respuesta.getBody();
        Assert.assertFalse(empleados.contains(empleado));
    }

    @Y("forma parte del proyecto {int}")
    public void formaParteDelProyecto(int arg0) {

    }

    @Entonces("recibo un mensaje de que no puedo dar de baja al empleado {string} porque forma parte de un proyecto")
    public void reciboUnMensajeDeQueNoPuedoDarDeBajaAlEmpleadoPorqueFormaParteDeUnProyecto(int arg0) {

    }

    @Y("tiene la tarea {int} asignada")
    public void tieneLaTareaAsignada(int arg0) {

    }

    @Y("recibo un mensaje de que no puedo dar de baja al empleado {string} porque tiene una tarea asignado")
    public void reciboUnMensajeDeQueNoPuedoDarDeBajaAlEmpleadoPorqueTieneUnaTareaAsignado(int arg0) {

    }

    @Y("tiene el ticket {int} asignado")
    public void tieneElTicketAsignado(int arg0) {

    }

    @Y("recibo un mensaje de que no puedo dar de baja al empleado {string} porque tiene un ticket asignado")
    public void reciboUnMensajeDeQueNoPuedoDarDeBajaAlEmpleadoPorqueTieneUnTicketAsignado(int arg0) {
    }
}
