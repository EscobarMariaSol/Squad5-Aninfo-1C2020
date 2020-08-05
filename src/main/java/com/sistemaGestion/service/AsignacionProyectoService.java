package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.AsignacionProyectoException;
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
        asignacionProyectoRepository.save(asignacionProyecto);
        empleado.addProyecto(asignacionProyecto);
        empleadoService.actualizarEmpleado(empleado);
        return asignacionProyecto;
    }

    public Set<AsignacionProyecto> obtenerProyectosDeEmpleado(String legajo) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        return empleado.getProyectosAsignados();
    }

    public AsignacionProyecto modificarAsignacionDeEmpleado(String legajo, Long idAsignacion, LocalDate fechaFin) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        AsignacionProyecto asignacionProyecto = asignacionProyectoRepository.findOne(idAsignacion);
        if (asignacionProyecto == null) {
            throw new AsignacionProyectoException(
                    "La asignación con id: " + idAsignacion + " no existe."
            );
        }
        asignacionProyecto.setFechaFin(fechaFin);
        asignacionProyectoRepository.save(asignacionProyecto);
        return asignacionProyecto;
    }
}
