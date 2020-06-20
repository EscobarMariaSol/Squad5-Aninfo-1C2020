package com.sistemaGestion.service;

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

}
