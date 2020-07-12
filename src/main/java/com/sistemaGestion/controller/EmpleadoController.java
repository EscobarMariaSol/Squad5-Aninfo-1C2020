package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.HorasCargadas;
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
        try {
            return new ResponseEntity<>(
                    empleadoService.ingresarEmpleado(nuevoEmpleado),
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
    public ResponseEntity actualizarEmpleado(
            @PathVariable("legajo") String legajo,
            @RequestParam(required = false) String seniority,
            @RequestParam(required = false) String rol
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

    private ResponseEntity asignarSeniority(String legajo, String seniority) {
        return new ResponseEntity(
                empleadoService.asignarSeniorityAEmpleado(legajo, seniority),
                HttpStatus.OK
        );
    }

    private ResponseEntity asignarRol(String legajo, String rol) {
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
    @GetMapping(value = "/{legajo}/proyectos/{proyectoId}/horas")
    public ResponseEntity obtenerHorasDeUnEmpleadoEnUnProyecto(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") String proyectoId){
        try {
            return new ResponseEntity(
                    empleadoService.obtenerHorasDeUnEmpleadoEnUnProyecto(legajo, proyectoId),
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
