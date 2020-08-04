package com.sistemaGestion.service;

import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.dtos.ReporteDeHorasDTO;
import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CargaDeHorasService {

    private CargaDeHorasRepository cargaDeHorasRepository;
    private EmpleadoService empleadoService;
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private void CargaDeHorasService(CargaDeHorasRepository cargaDeHorasRepository, EmpleadoService empleadoService, EmpleadoRepository empleadoRepository) {
        this.empleadoService = new EmpleadoService(empleadoRepository);
        this.cargaDeHorasRepository = cargaDeHorasRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado cargarHorasDeEmpleado(String legajo, ReporteDeHorasDTO reporte) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        if (laCargaNoCorrespondeAlMesVigente(reporte.getFecha())) {
            throw new HorasCargadasException("Solo se puede cargar horas en el mes vigente.");
        }
        CargaDeHoras cargaDeHoras = new CargaDeHoras(reporte.getActividad(), reporte.getTareaId(), reporte.getProyectoId(), reporte.getFecha(), reporte.getCantidadHoras(), legajo);
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
            throw new HorasCargadasException("El empleado con legajo: " + legajo +
                    "no pertenece al proyecto cuyo id es" + proyectoId);
        }
    }

    private boolean empleadoPerteneceAlProyecto(Empleado empleado, String proyectoId) {
        return empleado.getAsignacionProyectos().stream()
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
