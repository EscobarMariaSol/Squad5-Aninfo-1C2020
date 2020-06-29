package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(
                empleadoService.consultarEmpleados(),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/")
    public ResponseEntity ingresarEmpleado(@RequestBody Empleado nuevoEmpleado) {
        return new ResponseEntity<>(
                empleadoService.ingresarEmpleado(nuevoEmpleado),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity consultarEmpleado(
            @RequestParam(required = false) String legajo,
            @RequestParam(required = false) Long id
    ) {
        try {
            if ((legajo != null && id != null) || legajo == null && id == null){
                return new ResponseEntity(
                        "Por favor ingrese un único valor de búsqueda.",
                        HttpStatus.BAD_REQUEST
                );
            } else if (legajo != null) {
                return consultarEmpleadoPorLegajo(legajo);
            } else {
                return consultarEmpleadoPorId(id);
            }
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

    private ResponseEntity consultarEmpleadoPorId(Long id) {
        return new ResponseEntity(
                empleadoService.consultarEmpleadoPorId(id),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity asignarSeniority(@PathVariable("id") long id, String seniority) {
        try {
            return new ResponseEntity(
                    empleadoService.asignarSeniorityAEmpleado(id, seniority),
                    HttpStatus.OK
            );
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
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
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
