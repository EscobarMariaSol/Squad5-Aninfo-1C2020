package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemaGestion.model.Empleado;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    private Empleado empleado;

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
        empleado = consultarEmpleadoPorId(id);
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

    public Empleado darDeBajaEmpleado(String legajo) {
        Empleado empleado = empleadoRepository.findByLegajo(legajo)
                .orElseThrow(() ->
                        new EmpleadoException("Empleado with legajo " + legajo + " not found.")
                );
        empleado.setActivo(true);
        return empleadoRepository.save(empleado);
    }

}
