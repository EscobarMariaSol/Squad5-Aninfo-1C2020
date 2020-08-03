package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.exceptions.CargaDeHorasException;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.service.CargaDeHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/empleados")
public class CargaDeHorasController {


    private CargaDeHorasService cargaDeHorasService;

    @Autowired
    private CargaDeHorasController(CargaDeHorasService cargaDeHorasService){
        this.cargaDeHorasService = cargaDeHorasService;
    }

    @PostMapping(value = "/{legajo}/proyectos/{proyectoId}/tareas/{tareaId}/horas")
    public ResponseEntity cargarHorasDeEmpleadoEnUnaTarea(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") Long proyectoId, @PathVariable("tareaId") String tareaId, @RequestBody HorasCargadas horasCargadas) {
        try {
            return new ResponseEntity(
                    cargaDeHorasService.cargarHorasDeEmpleadoEnUnaTarea(legajo, proyectoId, tareaId, horasCargadas),
                    HttpStatus.OK
            );
        } catch (EmpleadoException | IOException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (CargaDeHorasException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping(value = "/{legajo}/proyectos/{proyectoId}/horas")
    public ResponseEntity obtenerHorasDeUnEmpleadoEnUnProyecto(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") String proyectoId) {
        try {
            return new ResponseEntity(
                    cargaDeHorasService.obtenerHorasDeUnEmpleadoEnUnProyecto(legajo, proyectoId),
                    HttpStatus.OK
            );
        } catch (CargaDeHorasException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/{legajo}/proyectos/{proyectoId}/tareas/{tareaId}/horas")
    public ResponseEntity mostrarHorasEnUnaTarea(@PathVariable("legajo")String legajo,@PathVariable("tareaId") String tareaId,@PathVariable("proyectoId") String proyectoId, String fecha) {
        try {
            return new ResponseEntity(
                    cargaDeHorasService.consultarHorasTrabajadasEnUnaTarea(legajo, tareaId, Long.parseLong(proyectoId), fecha),
                    HttpStatus.OK
            );
        } catch (CargaDeHorasException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
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
                    cargaDeHorasService.obtenerHorasDeUnEmpleadoConFiltros(legajo, tareaId, proyectoId, fechaInicio, fechaFin),
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
