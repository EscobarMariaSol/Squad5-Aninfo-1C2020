package com.sistemaGestion.controller;

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

    @PostMapping(value = "/empleado/")
    public ResponseEntity ingresarEmpleado(@RequestBody Empleado nuevoEmpleado) {
        return new ResponseEntity<>(
                empleadoService.ingresarEmpleado(nuevoEmpleado),
                HttpStatus.OK
        );
    }

}
