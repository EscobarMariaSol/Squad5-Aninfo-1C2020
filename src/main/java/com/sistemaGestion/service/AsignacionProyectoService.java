package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.EmpleadoNoAsignadoException;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class AsignacionProyectoService {

    private AsignacionProyectoRepository asignacionProyectoRepository;
    private EmpleadoService empleadoService;

    @Autowired
    public AsignacionProyectoService(AsignacionProyectoRepository asignacionProyectoRepository, EmpleadoService empleadoService) {
        this.asignacionProyectoRepository = asignacionProyectoRepository;
        this.empleadoService = empleadoService;
    }


    public AsignacionProyecto asignarEmpleadoAProyecto(String legajo, AsignacionProyecto asignacionProyecto) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        asignacionProyecto.setLegajoEmpleado(legajo);
        asignacionProyectoRepository.save(asignacionProyecto);
        empleado.addProyecto(asignacionProyecto);
        return asignacionProyecto;
    }

    public Set<AsignacionProyecto> obtenerProyectosDeEmpleado(String legajo) {
        empleadoService.consultarEmpleadoPorLegajo(legajo);
        return asignacionProyectoRepository.findByLegajoEmpleado(legajo);
    }

    public AsignacionProyecto modificarAsignacionDeEmpleado(String legajo, Long proyectoId, LocalDate fechaFin) {
       empleadoService.consultarEmpleadoPorLegajo(legajo);
        AsignacionProyecto asignacion = asignacionProyectoRepository.findByCodigoProyectoAndLegajoEmpleado(proyectoId, legajo).orElseThrow(() ->
                new EmpleadoNoAsignadoException("El empleado con legajo " + legajo + " no fue asignado al proyecto" + proyectoId + ".")
        );
        asignacion.setFechaFin(fechaFin);
        asignacionProyectoRepository.save(asignacion);
        return asignacion;
    }
}
