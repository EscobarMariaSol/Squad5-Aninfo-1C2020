package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.model.enums.EmpleadoContrato;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReporteDeHorasDTO {

    private Actividad actividad;
    private String tareaId;
    private String proyectoId;
    private LocalDate fecha;
    private Float cantidadHoras;

    public ReporteDeHorasDTO() {
    }

    public ReporteDeHorasDTO(Actividad actividad, String tareaId, String proyectoId, LocalDate fecha, Float cantidadHoras){
        this.tareaId = tareaId;
        this.actividad = actividad;
        this.fecha = fecha;
        this.cantidadHoras = cantidadHoras;
        this.proyectoId = proyectoId;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Float cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    @Override
    public String toString() {
        return "ReporteDeHorasDTO{" +
                "Fecha: " + fecha +
                ", Horas: " + cantidadHoras +
                ", Activida: '" + actividad + '\'' +
                ", Tarea: '" + tareaId + '\'' +
                '}';
    }
}
