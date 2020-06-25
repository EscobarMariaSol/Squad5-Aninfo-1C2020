package com.sistemaGestion.controller;

<<<<<<< HEAD
import com.sistemaGestion.model.Empleado;
=======
import com.sistemaGestion.exceptions.EmpleadoException;
>>>>>>> a22beb3ee77ea45729ce07a8b72f6c06d867265c
import com.sistemaGestion.service.EmpleadoService;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> a22beb3ee77ea45729ce07a8b72f6c06d867265c

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

<<<<<<< HEAD
    @PutMapping
    public String asignarSeniority() {
        return "Seniority Asignada";
    }
=======
    @GetMapping(value = "/{legajo}")
    public ResponseEntity consultarEmpleado(@PathVariable("legajo") String legajo) {
        try {
            return new ResponseEntity(
                    empleadoService.consultarEmpleadoPorLegajo(legajo),
                    HttpStatus.OK
            );
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

>>>>>>> a22beb3ee77ea45729ce07a8b72f6c06d867265c
}
