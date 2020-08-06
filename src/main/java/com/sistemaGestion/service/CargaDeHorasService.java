package com.sistemaGestion.service;

import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.exceptions.CargaDeHorasException;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
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
    private AsignacionProyectoRepository asignacionProyectoRepository;
    private EmpleadoService empleadoService;
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProyectosRequester proyectosRequester;

    @Value("${proyectos.uri}")
    private String uri;


    @Autowired
    private void CargaDeHorasService(
            CargaDeHorasRepository cargaDeHorasRepository,
            EmpleadoService empleadoService,
            EmpleadoRepository empleadoRepository,
            AsignacionProyectoRepository asignacionProyectoRepository) {
        this.empleadoService = new EmpleadoService(empleadoRepository);
        this.cargaDeHorasRepository = cargaDeHorasRepository;
        this.empleadoRepository = empleadoRepository;
        this.asignacionProyectoRepository = asignacionProyectoRepository;
    }


    public CargaDeHoras cargarHorasDeEmpleado(String legajo, ReporteDeHorasDTO reporte) throws IOException {
        validarCargaDeHoras(legajo, reporte);
        Long proyectoId = reporte.getActividad().equals(Actividad.TAREA) ? reporte.getProyectoId() : null;
        CargaDeHoras cargaDeHoras = new CargaDeHoras(reporte.getActividad(), reporte.getTareaId(), proyectoId, reporte.getFecha(), reporte.getCantidadHoras(), legajo);
        return cargaDeHorasRepository.save(cargaDeHoras);
    }

    private void validarCargaDeHoras(String legajo, ReporteDeHorasDTO reporte) throws IOException {
        empleadoService.consultarEmpleadoPorLegajo(legajo);

        if (laCargaNoCorrespondeAlMesVigente(reporte.getFecha())) {
            throw new CargaDeHorasException("Solo se puede cargar horas en el mes vigente.");
        }
        if(reporte.getActividad().equals(Actividad.TAREA) && ! proyectosRequester.empleadoTieneAsignadaLaTarea(
                String.valueOf(reporte.getProyectoId()), String.valueOf(reporte.getTareaId()))) {
            throw new CargaDeHorasException("Solo se puede cargar horas en una tarea que tiene asignada.");
        }
    }

    private boolean laCargaNoCorrespondeAlMesVigente(LocalDate fecha) {
        return ! fecha.getMonth().equals(LocalDate.now().getMonth()) && (LocalDate.now().getYear() == fecha.getYear());
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
        return empleado.getProyectosAsignados().stream()
                .anyMatch(asignacionProyecto -> asignacionProyecto.getCodigoProyecto().equals(Long.parseLong(proyectoId)));
    }

    public List<HorasCargadas> consultarHorasTrabajadasEnUnaTarea(String legajo, Long tareaId, Long proyectoId, String fecha) {
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


    public List<ReporteDeHorasDTO> obtenerHorasDeUnEmpleadoConFiltros(
            String legajo, Actividad actividad, Long tareaId, Long proyectoId, String fechaInicio, String fechaFin) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        List<ReporteDeHorasDTO> reporteDeHoras = new ArrayList<ReporteDeHorasDTO>();
        List<CargaDeHoras> horasTrabajadas;
        LocalDate fecha1 = (fechaInicio == null) ? LocalDate.parse("2000-01-01") : LocalDate.parse(fechaInicio);
        LocalDate fecha2 = (fechaFin == null) ? LocalDate.now() : LocalDate.parse(fechaFin);
        if (tareaId == null && proyectoId != null && actividad.name().equals("TAREA")) {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, proyectoId, fecha1, fecha2);
        } else if (tareaId != null && proyectoId == null && actividad.name().equals("TAREA")) {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, tareaId, fecha1, fecha2);
        } else if (tareaId != null && actividad.name().equals("TAREA")) {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo, tareaId, proyectoId, fecha1, fecha2);
        } else if (actividad != null && tareaId == null && proyectoId == null) {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndActividadAndFechaGreaterThanEqualAndFechaLessThanEqual(
                    legajo, actividad, fecha1, fecha2);
        } else {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
                    legajo,fecha1, fecha2);
        }
        rellenarReporte(reporteDeHoras, horasTrabajadas, tareaId, proyectoId);
        return reporteDeHoras;
    }

    private void rellenarReporte(List<ReporteDeHorasDTO> reporteDeHoras, List<CargaDeHoras> listadoDeHorasCargadas, Long tareaId, Long proyectoId) {
        for (CargaDeHoras cargaDeHoras: listadoDeHorasCargadas) {
            reporteDeHoras.add(new ReporteDeHorasDTO(
                    cargaDeHoras.getActividad(),
                    cargaDeHoras.getTareaId(),
                    cargaDeHoras.getProyectoId(),
                    cargaDeHoras.getFecha(),
                    cargaDeHoras.getHorasTrabajadas()
            ));
        }
    }
}
