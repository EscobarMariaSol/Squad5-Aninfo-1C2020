package com.sistemaGestion.service;

import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.*;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.model.enums.Seniority;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    private AsignacionProyectoRepository asignacionProyectoRepository;
    private CargaDeHorasRepository cargaDeHorasRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, CargaDeHorasRepository cargaDeHorasRepository) {
        this.empleadoRepository = empleadoRepository;
        this.cargaDeHorasRepository = cargaDeHorasRepository;
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


    public Empleado asignarAProyecto(String legajo, AsignacionProyecto asignacionProyecto) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        empleado.addProyecto(asignacionProyecto);
        asignacionProyecto.setRolEmpleado(empleado.getRol().name());
        empleadoRepository.save(empleado);
        return empleado;
    }

    public List<HorasCargadas> consultarHorasTrabajadasEnUnaTarea(String legajo, String tareaId, String proyectoId, String fecha) {
        List<CargaDeHoras> horasTrabajadas =  new ArrayList<CargaDeHoras>();
        consultarEmpleadoPorLegajo(legajo);
        if (fecha == null) {
             horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoId(legajo, tareaId, proyectoId);
        } else {
            horasTrabajadas = cargaDeHorasRepository.findByLegajoAndTareaIdAndProyectoIdAndFecha(legajo, tareaId, proyectoId, LocalDate.parse(fecha));
        }
        if (horasTrabajadas.isEmpty()) throw new HorasCargadasException("CargaDeHoras with legajo " + legajo +
                "with tareaId " + tareaId +
                "with proyectoId " + proyectoId + "not found.");
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
}
