package com.sistemaGestion.service;

import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public void asignarEmpleadoAProyecto(String legajo, AsignacionProyecto asignacionProyecto) {
        asignacionProyectoRepository.save(asignacionProyecto);
        empleadoService.asignarAProyecto(legajo, asignacionProyecto);
    }

    public Set<AsignacionProyecto> obtenerProyectosDeEmpleado(String legajo) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        return empleado.getAsignacionProyectos();
    }
}
