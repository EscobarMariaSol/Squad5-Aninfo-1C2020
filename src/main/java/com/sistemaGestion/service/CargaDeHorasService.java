package com.sistemaGestion.service;

import com.sistemaGestion.dtos.CargaDeHorasDTO;
import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.repository.CargaDeHorasRepository;
import com.sistemaGestion.repository.EmpleadoRepository;

import java.time.LocalDate;

public class CargaDeHorasService {

    private CargaDeHorasRepository cargaDeHorasRepository;
    private EmpleadoService empleadoService;
    private EmpleadoRepository empleadoRepository;

    public Empleado cargarHorasDeEmpleadoEnUnaTarea(String legajo, String proyectoId, String tareaId, HorasCargadas horasCargadas) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);
        if (laCargaNoCorrespondeAlMesVigente(horasCargadas.getFecha())) {
            throw new HorasCargadasException("Solo se puede cargar horas en el mes vigente.");
        }
        CargaDeHoras cargaDeHoras = new CargaDeHoras(tareaId, proyectoId, horasCargadas.getFecha(), horasCargadas.getHoras(), legajo);
        empleado.cargarHoras(cargaDeHoras);
        return empleadoRepository.save(empleado);
    }

    private boolean laCargaNoCorrespondeAlMesVigente(LocalDate fecha) {
        return fecha.getMonth().equals(LocalDate.now().getMonth());
    }

    public CargaDeHorasDTO obtenerHorasDeUnEmpleadoEnUnProyecto(String legajo, String proyectoId) {
        Empleado empleado = empleadoService.consultarEmpleadoPorLegajo(legajo);

        if (empleadoPerteneceAlProyecto(empleado, proyectoId)){
            Float cantidadDeHoras = cargaDeHorasRepository.findByProyectoIdAndLegajo(proyectoId, legajo).stream()
                    .map(CargaDeHoras::getHorasTrabajadas)
                    .reduce(Float::sum).orElse(Float.valueOf(0));
            return new CargaDeHorasDTO(legajo, cantidadDeHoras, proyectoId, empleado.getContrato());
        }else{
            throw new HorasCargadasException("El empleado con legajo: " + legajo +
                    "no pertenece al proyecto cuyo id es" + proyectoId);
        }
    }

    private boolean empleadoPerteneceAlProyecto(Empleado empleado, String proyectoId) {
        return empleado.getAsignacionProyectos().stream()
                .anyMatch(asignacionProyecto -> asignacionProyecto.getCodigo().equals(proyectoId));
    }
}
