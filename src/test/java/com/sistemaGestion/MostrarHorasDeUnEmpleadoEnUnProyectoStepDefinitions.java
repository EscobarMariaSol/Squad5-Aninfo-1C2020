package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasControler;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.model.*;
import com.sistemaGestion.model.enums.EmpleadoContrato;
import com.sistemaGestion.model.enums.EmpleadoRol;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MostrarHorasDeUnEmpleadoEnUnProyectoStepDefinitions {

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private CargaDeHorasControler cargaDeHorasControler;

    @Autowired
    private com.sistemaGestion.controller.AsignacionProyectoController asignacionProyectoController;

    private Empleado liderRRHH;
    private Empleado empleado;
    private PerfilEmpleadoDTO perfilEmpleadoDTO;
    private ResponseEntity response;
    private AsignacionProyecto asignacionProyecto;


    @Dado("que soy jefe de RRHH")
    public void dado_que_soy_jefe_de_recursos_humanos() {
        liderRRHH = EmpleadoFactory.crearLiderDeRecursosHumanos();
    }

    @Y("tengo un empleado con los siguientes datos")
    public void tengo_un_empleado_con_los_siguientes_datos(DataTable empleadoTable) {
        List<Map<String, String>> empleados = empleadoTable.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    @Y("este empleado carga las horas trabajadas en las siguientes tareas")
    public void este_empleado_ha_cargado_las_horas_trabajadas_en_las_siguientes_tareas(DataTable horasTable) {
        List<Map<String, String>> horasCargadas = horasTable.asMaps(String.class, String.class);
        horasCargadas.stream().forEach(datosHora ->{
            cargaDeHorasControler.cargarHorasDeEmpleadoEnUnaTarea(
                    empleado.getLegajo(),
                    Long.parseLong(datosHora.get("proyectoId")),
                    datosHora.get("tareaId"),
                    new HorasCargadas(
                            datosHora.get("fechaCargaDeHoras"),
                            Float.valueOf(datosHora.get("horasTrabajadas"))));
        });
    }

    @Y("el empleado es asignado a ese proyecto cuyo id es {string}")
    public void el_empleado_es_asignado_a_ese_proyecto_cuyo_id_es_pero_no_ha_cargado_horas_aun(String idProyecto) {
        LocalDate fechaInicio = LocalDate.parse("2020-06-07");
        LocalDate fechaFin = LocalDate.parse("2020-06-16");
        EmpleadoRol rol = EmpleadoRol.DESARROLLADOR;
        asignacionProyecto = new AsignacionProyecto(Long.parseLong(idProyecto), fechaInicio, fechaFin, rol);
        response = asignacionProyectoController.asignarEmpleadoAProyecto(empleado.getLegajo(), asignacionProyecto);
    }

    @Cuando("consulto las horas trabajadas por el empleado en el proyecto cuyo id es {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_en_el_proyecto_cuyo_id_es(String proyectoId) {
        response = cargaDeHorasControler.obtenerHorasDeUnEmpleadoEnUnProyecto(empleado.getLegajo(), proyectoId);

    }

    @Entonces("obtengo la siguiente informacion")
    public void obtengo_la_siguiente_informacion(DataTable horasTrabajadasTable) {
        List<Map<String, String>> horasTrabajadas =  horasTrabajadasTable.asMaps(String.class, String.class);
        CargaDeHorasDTO horasEsperadasTrabajadas = new CargaDeHorasDTO(
                horasTrabajadas.get(0).get("legajo"),
                Float.valueOf(horasTrabajadas.get(0).get("cantidadDeHorasTrabajadas")),
                horasTrabajadas.get(0).get("nombreDeProyecto"),
                EmpleadoContrato.valueOf(horasTrabajadas.get(0).get("tipoDeContrato").toUpperCase())
        );
        CargaDeHorasDTO horasObtenidas = (CargaDeHorasDTO) response.getBody();
        Assert.assertEquals(horasEsperadasTrabajadas, horasObtenidas);
    }

    @Entonces("obtengo un mensaje que me indica que el empleado no tiene horas trabajadas en el proyecto ya que este no estaba asignado a el.")
    public void obtengo_un_mensaje_que_me_indica_que_el_empleado_no_tiene_horas_trabajadas_en_el_proyecto() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
