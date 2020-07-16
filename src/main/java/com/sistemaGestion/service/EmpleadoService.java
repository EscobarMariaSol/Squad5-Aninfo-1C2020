package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.*;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        new EmpleadoException("El empleado con legajo " + legajo + " no existe.")
                );
    }

    public Empleado actualizarEmpleado(Empleado empleado) {
        empleadoRepository.findByLegajo(empleado.getLegajo()).orElseThrow(() ->
            new EmpleadoException("El empleado con legajo " + empleado.getLegajo() + " no existe.")
        );
        return empleadoRepository.save(empleado);
    }

    public Empleado asignarSeniorityAEmpleado(String legajo, Seniority seniority) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.setSeniority(seniority);
        empleadoRepository.save(empleado);
        return empleado;
    }

    private void validarDarDeBaja(Empleado empleado) {
        if (empleado.getAsignacionProyectos().size() > 0)
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

    public Empleado asignarRolAEmpleado(String legajo, EmpleadoRol rol) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.setRol(rol);
        empleadoRepository.save(empleado);
        return empleado;
    }

    public Empleado cargarHorasDeEmpleadoEnUnaTarea(String legajo, String proyectoId, String tareaId, HorasCargadas horasCargadas) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        CargaDeHoras cargaDeHoras = new CargaDeHoras(tareaId, proyectoId, horasCargadas.getFecha(), horasCargadas.getHoras());
        empleado.cargarHoras(cargaDeHoras);
        return empleadoRepository.save(empleado);
    }

    public Empleado asignarAProyecto(String legajo, AsignacionProyecto asignacionProyecto) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.addProyecto(asignacionProyecto);
        asignacionProyecto.setRolEmpleado(empleado.getRol().name());
        empleadoRepository.save(empleado);
        return empleado;
    }
}
