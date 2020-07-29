package com.sistemaGestion.controller;

import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.model.enums.Seniority;
import com.sistemaGestion.model.enums.EmpleadoRol;
import com.sistemaGestion.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/empleados")
public class EmpleadoController {

    private EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping(value = "/")
    public ResponseEntity consultarEmpleados() {
        List<PerfilEmpleadoDTO> empleadosADevolver = new ArrayList<>();
        for (Empleado empleado : empleadoService.consultarEmpleados()) {
            empleadosADevolver.add(PerfilEmpleadoDTO.convertirAEmpleadoDTO(empleado));
        }

        return new ResponseEntity<>(
                empleadosADevolver,
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/")
    public ResponseEntity ingresarEmpleado(@RequestBody PerfilEmpleadoDTO perfilEmpleado) {
        try {
            Empleado empleadoAIngresar = new Empleado();
            empleadoAIngresar = perfilEmpleado.convertirAEmpleadoModelo(empleadoAIngresar);
            return new ResponseEntity<>(
                    empleadoService.ingresarEmpleado(empleadoAIngresar),
                    HttpStatus.OK
            );
        } catch(Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/{legajo}")
    public ResponseEntity consultarEmpleado(@PathVariable String legajo) {
        try {
            return new ResponseEntity(
                    PerfilEmpleadoDTO.convertirAEmpleadoDTO(
                            empleadoService.consultarEmpleadoPorLegajo(legajo)
                    ),
                    HttpStatus.OK
            );
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(value = "/{legajo}")
    public ResponseEntity actualizarEmpleado(@RequestBody PerfilEmpleadoDTO perfilEmpleado){
        try {
            Empleado empleadoAActualizar = empleadoService.consultarEmpleadoPorLegajo(
                    perfilEmpleado.getLegajo()
            );
            empleadoAActualizar = perfilEmpleado.convertirAEmpleadoModelo(empleadoAActualizar);
            empleadoService.actualizarEmpleado(empleadoAActualizar);
            return new ResponseEntity(HttpStatus.OK);
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping(value = "/{legajo}")
    public ResponseEntity actualizarParcialmenteEmpleado(
            @PathVariable("legajo") String legajo,
            @RequestParam(required = false) Seniority seniority,
            @RequestParam(required = false) EmpleadoRol rol
    ) {
        try {
            if ((seniority != null && rol != null) || (seniority == null && rol == null))
                return new ResponseEntity(
                        "Por favor ingrese un campo.",
                        HttpStatus.BAD_REQUEST
                );
            if (seniority != null) {
                return asignarSeniority(legajo, seniority);
            } else {
                return asignarRol(legajo, rol);
            }
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    private ResponseEntity asignarSeniority(String legajo, Seniority seniority) {
        empleadoService.asignarSeniorityAEmpleado(legajo, seniority);
        return new ResponseEntity(
                HttpStatus.OK
        );
    }

    private ResponseEntity asignarRol(String legajo, EmpleadoRol rol) {
        empleadoService.asignarRolAEmpleado(legajo, rol);
        return new ResponseEntity(
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/{legajo}")
    public ResponseEntity darDeBajaEmpleado(@PathVariable("legajo") String legajo) {
        try {
            empleadoService.darDeBajaEmpleado(legajo);
            return new ResponseEntity(
                    HttpStatus.OK
            );
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @PostMapping(value = "/{legajo}/proyectos/{proyectoId}/tareas/{tareaId}/horas")
    public ResponseEntity cargarHorasDeEmpleadoEnUnaTarea(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") String proyectoId, @PathVariable("tareaId") String tareaId, HorasCargadas horasCargadas) {
        try {
            return new ResponseEntity(
                    empleadoService.cargarHorasDeEmpleadoEnUnaTarea(legajo, proyectoId, tareaId, horasCargadas),
            HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @GetMapping(value = "/{legajo}/proyectos/{proyectoId}/horas")
    public ResponseEntity obtenerHorasDeUnEmpleadoEnUnProyecto(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") Long proyectoId) {
        try {
            return new ResponseEntity(
                    empleadoService.obtenerHorasDeUnEmpleadoEnUnProyecto(legajo, proyectoId),
                    HttpStatus.OK
            );
        } catch (HorasCargadasException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/{legajo}/proyectos/{proyectoId}/tareas/{tareaId}/horas")
    public ResponseEntity mostrarHorasEnUnaTarea(
            @PathVariable("legajo") String legajo,
            @PathVariable("tareaId") String idTarea,
            @PathVariable("proyectoId") String idProyecto, String fecha) {
        try {
            return new ResponseEntity(
                    empleadoService.consultarHorasTrabajadasEnUnaTarea(legajo, idTarea, idProyecto, fecha),
                    HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(value = "/{legajo}/horas" )
    public ResponseEntity obtenerHorasTrabajadasDeUnEmpleadoConFiltros(
            @PathVariable("legajo") String legajo,
            @RequestParam(required = false) String tareaId,
            @RequestParam(required = false) String proyectoId,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin) {
        try {
            return new ResponseEntity(
                    empleadoService.obtenerHorasDeUnEmpleadoConFiltros(legajo, tareaId, proyectoId, fechaInicio, fechaFin),
                    HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
