package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.EmpleadoRol;
import com.sistemaGestion.model.Proyecto;
import com.sistemaGestion.repository.EmpleadoRepository;
import com.sistemaGestion.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemaGestion.model.Empleado;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> consultarEmpleados() {
        return empleadoRepository.findAllByActivoIsTrue();
    }

    public Empleado ingresarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado consultarEmpleadoPorLegajo(String legajo) {
        return empleadoRepository.findByLegajoAndActivoIsTrue(legajo)
                .orElseThrow( () ->
                        new EmpleadoException("Empleado with legajo " + legajo + " not found.")
                );
    }

    public Empleado asignarSeniorityAEmpleado(String legajo, String seniority) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.setSeniority(seniority);
        empleadoRepository.save(empleado);
        return empleado;
    }

    private void validarDarDeBaja(Empleado empleado) {
        if (empleado.getProyectos().size() > 0)
            throw new EmpleadoException(
                    "No se puede dar de baja al empleado con legajo: " +
                    empleado.getLegajo() +
                    " porque forma parte de algún proyecto."
            );
    }

    public Empleado darDeBajaEmpleado(String legajo) {
        Empleado empleado = empleadoRepository.findByLegajo(legajo)
                .orElseThrow(() ->
                        new EmpleadoException("Empleado with legajo " + legajo + " not found.")
                );
        validarDarDeBaja(empleado);
        empleado.setActivo(false);
        return empleadoRepository.save(empleado);
    }

    public Empleado asignarRolAEmpleado(String legajo, String rol) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        EmpleadoRol nuevoRol = EmpleadoRol.valueOf(rol.toUpperCase());
        empleado.setRol(nuevoRol);
        empleadoRepository.save(empleado);
        return empleado;
    }

    public Empleado asignarAProyecto(String legajo, Proyecto proyecto) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.addProyecto(proyecto);
        empleadoRepository.save(empleado);
        return empleado;
    }
}
