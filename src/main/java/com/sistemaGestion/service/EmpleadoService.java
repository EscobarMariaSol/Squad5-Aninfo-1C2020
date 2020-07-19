package com.sistemaGestion.service;

import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.*;
import com.sistemaGestion.repository.AsignacionProyectoRepository;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Empleado asignarRolAEmpleado(String legajo, String rol) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        EmpleadoRol nuevoRol = EmpleadoRol.valueOf(rol.toUpperCase());
        empleado.setRol(nuevoRol);
        empleadoRepository.save(empleado);
        return empleado;
    }

    public Empleado cargarHorasDeEmpleadoEnUnaTarea(String legajo, String proyectoId, String tareaId, HorasCargadas horasCargadas) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);
        CargaDeHoras cargaDeHoras = new CargaDeHoras(tareaId, proyectoId, horasCargadas.getFecha(), horasCargadas.getHoras(), legajo);
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

    public HorasTrabajadas obtenerHorasDeUnEmpleadoEnUnProyecto(String legajo, String proyectoId) {
        Empleado empleado = consultarEmpleadoPorLegajo(legajo);

        if (empleadoPerteneceAlProyecto(empleado, proyectoId)){
            Integer cantidadDeHoras = cargaDeHorasRepository.findByProyectoIdAndLegajo(proyectoId, legajo).stream()
                    .map(CargaDeHoras::getHorasTrabajadas)
                    .reduce(Integer::sum).orElse(0);
            return new HorasTrabajadas(legajo, cantidadDeHoras, proyectoId, empleado.getContrato());
        }else{
            throw new HorasCargadasException("El empleado con legajo: " + legajo +
                    "no pertenece al proyecto cuyo id es" + proyectoId);
        }
    }

    private boolean empleadoPerteneceAlProyecto(Empleado empleado, String proyectoId) {
        return empleado.getAsignacionProyectos().stream()
                .anyMatch(asignacionProyecto -> asignacionProyecto.getCodigo().equals(proyectoId));
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
