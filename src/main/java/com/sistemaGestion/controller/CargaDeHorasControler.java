package com.sistemaGestion.controller;

import com.sistemaGestion.exceptions.EmpleadoException;
import com.sistemaGestion.exceptions.HorasCargadasException;
import com.sistemaGestion.model.HorasCargadas;
import com.sistemaGestion.service.CargaDeHorasService;
import com.sistemaGestion.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class CargaDeHorasControler {


    private CargaDeHorasService cargaDeHorasService;
    private EmpleadoService empleadoService;

    @PostMapping(value = "/{legajo}/proyectos/{proyectoId}/tareas/{tareaId}/horas")
    public ResponseEntity cargarHorasDeEmpleadoEnUnaTarea(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") String proyectoId, @PathVariable("tareaId") String tareaId, HorasCargadas horasCargadas) {
        try {
            return new ResponseEntity(
                    cargaDeHorasService.cargarHorasDeEmpleadoEnUnaTarea(legajo, proyectoId, tareaId, horasCargadas),
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
    public ResponseEntity obtenerHorasDeUnEmpleadoEnUnProyecto(@PathVariable("legajo") String legajo, @PathVariable("proyectoId") String proyectoId) {
        try {
            return new ResponseEntity(
                    cargaDeHorasService.obtenerHorasDeUnEmpleadoEnUnProyecto(legajo, proyectoId),
                    HttpStatus.OK
            );
        } catch (HorasCargadasException e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(value = "/{legajo}/proyectos/{proyectoId/tareas/{tareaId}/horas")
    public ResponseEntity mostrarHorasEnUnaTarea(String legajo, String idTarea, String idProyecto, String fecha) {
        try {
            return new ResponseEntity(
                    empleadoService.consultarHorasTrabajadasEnUnaTarea(legajo, idTarea, idProyecto, fecha),
                    HttpStatus.OK
            );
        } catch (HorasCargadasException e) {
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
}
