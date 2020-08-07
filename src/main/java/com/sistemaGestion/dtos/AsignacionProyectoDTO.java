package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.EmpleadoRol;

import java.time.LocalDate;

public class AsignacionProyectoDTO {

    private Long codigoProyecto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EmpleadoRol rolEmpleado;

    public AsignacionProyectoDTO(){}

    public AsignacionProyectoDTO(Long codigoProyecto, LocalDate fechaInicio, LocalDate fechaFin, EmpleadoRol rolEmpleado) {
        this.codigoProyecto = codigoProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.rolEmpleado = rolEmpleado;
    }

    public EmpleadoRol getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(EmpleadoRol rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(Long codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    @Override
    public String toString() {
        return "AsignacionProyectoDTO{" +
                "codigoProyecto=" + codigoProyecto +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", rolEmpleado=" + rolEmpleado +
                '}';
    }
}
