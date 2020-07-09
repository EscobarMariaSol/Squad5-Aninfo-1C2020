package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.service.AsignacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empleados")
public class AsignacionProyectoController {

    private AsignacionProyectoService asignacionProyectoService;

    @Autowired
    public AsignacionProyectoController(AsignacionProyectoService asignacionProyectoService) {
        this.asignacionProyectoService = asignacionProyectoService;
    }

    @PostMapping(value = "/{legajo}/proyectos")
    public ResponseEntity asignarEmpleadoAProyecto(
            @PathVariable("legajo")  String legajo,
            @RequestBody AsignacionProyecto asignacionProyecto) {
        try {
            asignacionProyectoService.asignarEmpleadoAProyecto(legajo, asignacionProyecto);
            return new ResponseEntity(
                    HttpStatus.OK
            );
        } catch(EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch(Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
