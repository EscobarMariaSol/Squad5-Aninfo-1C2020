package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.exceptions.AsignacionProyectoException;
import com.sistemaGestion.exceptions.FechaInvalidaException;
import com.sistemaGestion.model.AsignacionProyecto;
import com.sistemaGestion.service.AsignacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
            @PathVariable("legajo") String legajo,
            @RequestBody AsignacionProyecto asignacionProyecto) {
        try {
            return new ResponseEntity(
                    asignacionProyectoService.asignarEmpleadoAProyecto(legajo, asignacionProyecto),
                    HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/{legajo}/proyectos/")
    public ResponseEntity obtenerProyectosAsignados(@PathVariable("legajo") String legajo) {
        try{
            return new ResponseEntity(
                    asignacionProyectoService.obtenerProyectosDeEmpleado(legajo),
                    HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(value = "/{legajo}/proyectos")
    public ResponseEntity modificarAsignacionDeEmpleadoAProyecto(
            @PathVariable("legajo") String legajo,
            @RequestParam(required = true) Long idAsignacion,
            @RequestParam(required = true) String fechaFin) {
        try{
            return new ResponseEntity(
                    asignacionProyectoService.modificarAsignacionDeEmpleado(legajo, idAsignacion, LocalDate.parse(fechaFin)),
                    HttpStatus.OK
            );
        } catch (EmpleadoException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (FechaInvalidaException | AsignacionProyectoException e) {
            return new ResponseEntity(
                        e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

    }
}
