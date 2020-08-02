package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasController;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.java.After;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AplicarFiltrosStepDefinitions {

    @Autowired
    private EmpleadoController empleadoController;

    @Autowired
    private CargaDeHorasController cargaDeHorasController;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private com.sistemaGestion.controller.AsignacionProyectoController AsignacionProyectoController;

    private List<ReporteDeHorasDTO> reporteDeHoras;
    private Empleado empleado;
    private PerfilEmpleadoDTO perfilEmpleadoDTO;
    private ResponseEntity response;
    private AsignacionProyecto asignacionProyecto;

    @Y("existe un empleado con los siguientes datos")
    public void existe_un_empleado_con_los_siguientes_datos(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> empleados = dataTable.asMaps(String.class, String.class);
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    @Y("el empleado con legajo {string} es asignado al proyecto {string}")
    public void el_empleado_con_legajo_es_asignado_al_proyecto(String legajo, String idProyecto) {
        // Write code here that turns the phrase above into concrete actions
        LocalDate fechaInicio = LocalDate.parse("2020-06-07");
        LocalDate fechaFin = LocalDate.parse("2020-06-16");
        EmpleadoRol rol = EmpleadoRol.DESARROLLADOR;

        asignacionProyecto = new AsignacionProyecto(Long.parseLong(idProyecto), fechaInicio, fechaFin, rol);
        response = AsignacionProyectoController.asignarEmpleadoAProyecto(legajo, asignacionProyecto);

    }

    @Y("el empleado con legajo {string} realizo la siguiente carga de horas")
    public void el_empleado_con_legajo_realizo_la_siguiente_carga_de_horas(String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> horasCargadas = dataTable.asMaps(String.class, String.class);
        horasCargadas.stream().forEach(datosHora -> {
            ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                    Actividad.TAREA,
                    datosHora.get("tareaId"),
                    datosHora.get("proyectoId"),
                    LocalDate.parse(datosHora.get("fechaCargaDeHoras")),
                    Float.valueOf(datosHora.get("horasTrabajadas"))
            );
            cargaDeHorasController.cargarHorasDeEmpleado(
                    legajo,
                    reporte);
        });
    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} en el proyecto {string} aplicando los siguientes filtros")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_el_proyecto_aplicando_los_siguientes_filtros(
            String legajo, String proyectoId, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.stream().forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    filtro.get("tareaId"),
                    filtro.get("proyectoId"),
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });

    }

    @Entonces("se me devuelve la siguiente informacion")
    public void se_me_devuelve_la_siguiente_informacion(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        reporteDeHoras = (List<ReporteDeHorasDTO>) response.getBody();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} en el proyecto {string} aplicando los filtros")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_en_el_proyecto_aplicando_los_filtros(
            String legajo, String proyectoId, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.stream().forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    null ,
                    filtro.get("proyectoId"),
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} aplicando los siguientes filtros")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_aplicando_los_siguientes_filtros(
            String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.stream().forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    filtro.get("tareaId"),
                    null,
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });
    }

    @Entonces("recibo un mensaje indicandome que no existe dicho empleado")
    public void recibo_un_mensaje_indicandome_que_no_existe_dicho_empleado() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @After
    public void tearDown() {
        empleadoRepository.deleteAll();
    }
}
