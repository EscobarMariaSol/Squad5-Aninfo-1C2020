package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.EmpleadoRol;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemaGestion.model.Empleado;

import java.util.List;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> consultarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado ingresarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado consultarEmpleadoPorLegajo(String legajo) {
        return empleadoRepository.findByLegajo(legajo)
                .orElseThrow( () ->
                        new EmpleadoException("Empleado with legajo " + legajo + " not found.")
                );
    }

    public Empleado asignarSeniorityAEmpleado(long id, String seniority) {
        Empleado empleado = consultarEmpleadoPorId(id);
        empleado.setSeniority(seniority);
        empleadoRepository.save(empleado);
        return empleado;
    }

    public Empleado consultarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow( () ->
                        new EmpleadoException("Empleado with id " + id + " not found.")
                );
    }

    public Empleado asignarRolAEmpleado(String legajo, String rol) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        EmpleadoRol nuevoRol = EmpleadoRol.valueOf(rol.toUpperCase());
        empleado.setRol(nuevoRol);
        empleadoRepository.save(empleado);
        return empleado;
    }
}
