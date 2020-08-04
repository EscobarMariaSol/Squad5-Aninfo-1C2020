package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.CargaDeHorasController;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.model.AsignacionProyecto;
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

    private ResponseEntity response;

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
        PerfilEmpleadoDTO perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleadoController.ingresarEmpleado(perfilEmpleadoDTO);
    }

    @Y("el empleado con legajo {string} es asignado al proyecto {string}")
    public void el_empleado_con_legajo_es_asignado_al_proyecto(String legajo, String proyectoId) {
        // Write code here that turns the phrase above into concrete actions
        response = AsignacionProyectoController.asignarEmpleadoAProyecto(
                legajo, new AsignacionProyecto(
                        Long.parseLong(proyectoId),
                        LocalDate.parse("2020-07-30"),
                        LocalDate.parse("2020-09-10"),
                        EmpleadoRol.DESARROLLADOR
                )
        );
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
        horasCargadas.forEach(datosHora -> {
            ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                    Actividad.valueOf(datosHora.get("actividad")),
                    Long.parseLong(datosHora.get("tareaId")),
                    Long.parseLong(datosHora.get("proyectoId")),
                    LocalDate.parse(datosHora.get("fechaCargaDeHoras")),
                    Float.valueOf(datosHora.get("horasTrabajadas"))
            );
            cargaDeHorasController.cargarHorasDeEmpleado(legajo, reporte);
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
        filtros.forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    Actividad.valueOf(filtro.get("actividad")),
                    Long.parseLong(filtro.get("tareaId")),
                    Long.parseLong(filtro.get("proyectoId")),
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });
    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} filtrando por proyecto y fechas")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_filtrando_por_proyecto_y_fechas(String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
<<<<<<< HEAD
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    Actividad.TAREA,
                    null,
                    Long.parseLong(filtro.get("proyectoId")),
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });
=======
        reporteDeHoras = (ReporteDeHorasDTO) response.getBody();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Map<String, String>> datosEsperados = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> datoEsperado : datosEsperados) {
            Assert.assertTrue(reporteDeHoras.getHoras().containsKey(LocalDate.parse(datoEsperado.get("fecha"))));
            Assert.assertEquals(datoEsperado.get("proyectoId"), reporteDeHoras.getProyectoid());
            Assert.assertEquals(datoEsperado.get("tareaId"), reporteDeHoras.getTareaId());
            Integer horas = new Integer(datoEsperado.get("cantidadDeHorasTrabajadas"));
            Assert.assertEquals(
                    horas.intValue(),
                    reporteDeHoras.getHoras().get(LocalDate.parse(datoEsperado.get("fecha"))).intValue()
            );
        }
    }
>>>>>>> 6693386a8fc494e6eaa8e9f941180aad35428e1a

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} filtrando por tarea y fechas")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_filtrando_por_tarea_y_fechas(String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    Actividad.TAREA,
                    Long.parseLong(filtro.get("tareaId")),
                    null,
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} filtrando por un rango de fechas")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_filtrando_por_un_rango_de_fechas(String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        filtros.forEach(filtro -> {
            response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                    legajo,
                    Actividad.TAREA,
                    null,
                    null,
                    filtro.get("fechaInicio"),
                    filtro.get("fechaFin"));
        });

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} con filtro de actividad {string}")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_con_filtro_de_actividad(String legajo, String actividad) {
        // Write code here that turns the phrase above into concrete actions
        response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                legajo, Actividad.valueOf(actividad), null, null, null, null);
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
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ReporteDeHorasDTO> reportesObtenidos = (List<ReporteDeHorasDTO>) response.getBody();
        List<Map<String, String>> reportesEsperados = dataTable.asMaps(String.class, String.class);
        reportesEsperados.forEach(infoEsperada -> {
            ReporteDeHorasDTO reporteEsperado = new ReporteDeHorasDTO(
                    Actividad.valueOf(infoEsperada.get("actividad")),
                    Long.parseLong(infoEsperada.get("tareaId")),
                    Long.parseLong(infoEsperada.get("proyectoId")),
                    LocalDate.parse(infoEsperada.get("fecha")),
                    Float.valueOf(infoEsperada.get("cantidadDeHorasTrabajadas"))
            );
            Assert.assertTrue(reportesObtenidos.contains(reporteEsperado));
        });
    }

    @Y("el empleado con legajo {string} cargo horas con los siguientes datos")
    public void el_empleado_con_legajo_cargo_horas_con_los_siguientes_datos(String legajo, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> horasCargadas = dataTable.asMaps(String.class, String.class);
        horasCargadas.forEach(datosHora -> {
            ReporteDeHorasDTO reporte = new ReporteDeHorasDTO(
                    Actividad.valueOf(datosHora.get("actividad")),
                    null,
                    null,
                    LocalDate.parse(datosHora.get("fechaCargaDeHoras")),
                    Float.valueOf(datosHora.get("horasTrabajadas"))
            );
            cargaDeHorasController.cargarHorasDeEmpleado(legajo, reporte);
        });

    }

    @Cuando("consulto las horas trabajadas por el empleado con legajo {string} sin filtros")
    public void consulto_las_horas_trabajadas_por_el_empleado_con_legajo_sin_filtros(String legajo) {
        // Write code here that turns the phrase above into concrete actions
        response = cargaDeHorasController.obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
                legajo, null, null, null, null, null);

    }

    @Entonces("se me devuelve la siguiente informacion acerca de las horas cargadas")
    public void se_me_devuelve_la_siguiente_informacion_acerca_de_las_horas_cargadas(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ReporteDeHorasDTO> reportesObtenidos = (List<ReporteDeHorasDTO>) response.getBody();
        List<Map<String, String>> reportesEsperados = dataTable.asMaps(String.class, String.class);
        reportesEsperados.forEach(infoEsperada -> {
            ReporteDeHorasDTO reporteEsperado = new ReporteDeHorasDTO(
                    Actividad.valueOf(infoEsperada.get("actividad")),
                    null,
                    null,
                    LocalDate.parse(infoEsperada.get("fecha")),
                    Float.valueOf(infoEsperada.get("cantidadDeHorasTrabajadas"))
            );
            Assert.assertTrue(reportesObtenidos.contains(reporteEsperado));
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
