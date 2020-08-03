package com.sistemaGestion.service;

import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.exceptions.CargaDeHorasException;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CargaDeHorasService {

    private CargaDeHorasRepository cargaDeHorasRepository;
    private EmpleadoService empleadoService;
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProyectosRequester proyectosRequester;

    @Value("${proyectos.uri}")
    private String uri;


    @Autowired
    private void CargaDeHorasService(CargaDeHorasRepository cargaDeHorasRepository, EmpleadoService empleadoService, EmpleadoRepository empleadoRepository) {
        this.empleadoService = new EmpleadoService(empleadoRepository);
        this.cargaDeHorasRepository = cargaDeHorasRepository;
        this.empleadoRepository = empleadoRepository;
    }


    public Empleado cargarHorasDeEmpleado(String legajo, ReporteDeHorasDTO reporte) throws IOException {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        if (laCargaNoCorrespondeAlMesVigente(reporte.getFecha())) {
            throw new CargaDeHorasException("Solo se puede cargar horas en el mes vigente.");
        }
        if(reporte.getActividad().equals(Actividad.TAREA) && ! proyectosRequester.empleadoTieneAsignadaLaTarea(legajo, reporte.getTareaId())) {
            throw new CargaDeHorasException("Solo se puede cargar horas en una tarea que tiene asignada.");
        }
        Long proyectoId = reporte.getActividad().equals(Actividad.TAREA) ? Long.valueOf(reporte.getProyectoid()) : null;
        CargaDeHoras cargaDeHoras = new CargaDeHoras(reporte.getActividad(), reporte.getTareaId(), proyectoId, reporte.getFecha(), reporte.getCantidadHoras(), legajo);
        empleado.cargarHoras(cargaDeHoras);
        return empleadoRepository.save(empleado);
    }

    private boolean laCargaNoCorrespondeAlMesVigente(LocalDate fecha) {
        return ! fecha.getMonth().equals(LocalDate.now().getMonth());
    }

    public CargaDeHorasDTO obtenerHorasDeUnEmpleadoEnUnProyecto(String legajo, String proyectoId) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        if (empleadoPerteneceAlProyecto(empleado, proyectoId)){
            Float cantidadDeHoras = cargaDeHorasRepository.findByProyectoIdAndLegajo(Long.parseLong(proyectoId), legajo).stream()
                    .map(CargaDeHoras::getHorasTrabajadas)
                    .reduce(Float::sum).orElse((float) 0);
            return new CargaDeHorasDTO(legajo, cantidadDeHoras);
        }else{
            throw new CargaDeHorasException("El empleado con legajo: " + legajo +
                    "no pertenece al proyecto cuyo id es" + proyectoId);
        }
    }

    private boolean empleadoPerteneceAlProyecto(Empleado empleado, String proyectoId) {
        return empleado.getAsignacionProyectos().stream()
                .anyMatch(asignacionProyecto -> asignacionProyecto.getCodigoProyecto().equals(Long.parseLong(proyectoId)));
    }

    public List<HorasCargadas> consultarHorasTrabajadasEnUnaTarea(String legajo, String tareaId, Long proyectoId, String fecha) {
        List<CargaDeHoras> horasTrabajadas =  new ArrayList<CargaDeHoras>();
        empleadoService.consultarEmpleadoPorLegajo(legajo);
        if (fecha == null) {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoId(legajo, tareaId, proyectoId);
        } else {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoIdAndFecha(legajo, tareaId, proyectoId, LocalDate.parse(fecha));
        }
        return generarListadoDeHorasCargadas(horasTrabajadas);
    }

    private List<HorasCargadas> generarListadoDeHorasCargadas(List<CargaDeHoras> horasTrabajadas) {
        List<HorasCargadas> horasCargadas =  new ArrayList<HorasCargadas>();
        for (CargaDeHoras cargaDeHoras: horasTrabajadas) {
            horasCargadas.add(
                    new HorasCargadas(cargaDeHoras.getFecha().toString()
                            , cargaDeHoras.getHorasTrabajadas()));
        }
        return horasCargadas;
    }


    public ReporteDeHorasDTO obtenerHorasDeUnEmpleadoConFiltros(
            String legajo, String tareaId, String proyectoId, String fechaInicio, String fechaFin) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        ReporteDeHorasDTO reporteDeHoras = new ReporteDeHorasDTO(empleado.getContrato());
        List<CargaDeHoras> horasTrabajadas;
        LocalDate fecha1 = (fechaInicio == null) ? LocalDate.parse("2000-01-01") : LocalDate.parse(fechaInicio);
        LocalDate fecha2 = (fechaFin == null) ? LocalDate.now() : LocalDate.parse(fechaFin);
        if(tareaId == null && proyectoId != null){
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, Long.parseLong(proyectoId), fecha1, fecha2);
        } else if(tareaId != null && proyectoId == null){
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, tareaId, fecha1, fecha2);
        } else if(tareaId != null){
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, tareaId , Long.parseLong(proyectoId), fecha1, fecha2);
        } else {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo,fecha1, fecha2);
        }
        rellenarReporte(reporteDeHoras, generarListadoDeHorasCargadas(horasTrabajadas), tareaId, proyectoId);
        return reporteDeHoras;
    }

    private void rellenarReporte(ReporteDeHorasDTO reporteDeHoras, List<HorasCargadas> listadoDeHorasCargadas, String tareaId, String proyectoId) {
        reporteDeHoras.setProyectoid(proyectoId);
        reporteDeHoras.setTareaId(tareaId);
        for (HorasCargadas horas: listadoDeHorasCargadas) {
            reporteDeHoras.addHoras(horas.getFecha(), horas.getHoras());
        }
    }
}
