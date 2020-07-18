package com.sistemaGestion.controller;

import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
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
    public ResponseEntity consultarEmpleado(@PathVariable("legajo") String legajo) {
        try {
            return consultarEmpleadoPorLegajo(legajo);
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    private ResponseEntity consultarEmpleadoPorLegajo(String legajo) {
        return new ResponseEntity(
                empleadoService.consultarEmpleadoPorLegajo(legajo),
                HttpStatus.OK
        );
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
        return new ResponseEntity(
                empleadoService.asignarSeniorityAEmpleado(legajo, seniority),
                HttpStatus.OK
        );
    }

    private ResponseEntity asignarRol(String legajo, EmpleadoRol rol) {
        return new ResponseEntity(
                empleadoService.asignarRolAEmpleado(legajo, rol),
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
}
