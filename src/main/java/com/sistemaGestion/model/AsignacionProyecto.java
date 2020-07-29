package com.sistemaGestion.model;

import com.sistemaGestion.model.enums.EmpleadoRol;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class AsignacionProyecto {

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAsignacion;

    @Column
    private String codigoProyecto;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @Column
    private EmpleadoRol rolEmpleado;


    public AsignacionProyecto(){}

    public AsignacionProyecto(String codigo, LocalDate fechaInicio, LocalDate fechaFin, EmpleadoRol rol) {
        this.codigoProyecto = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.rolEmpleado = rol;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigo) {
        this.codigoProyecto = codigo;
    }

    public void setFechaInicio(LocalDate fecha) {
        this.fechaInicio = fecha;
    }

    public LocalDate getFechaInicio(){
        return this.fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaFin(){
        return this.fechaFin;
    }

    public void setRolEmpleado(EmpleadoRol rol) {
        this.rolEmpleado = rol;
    }

    public EmpleadoRol getRolEmpleado() {
        return this.rolEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AsignacionProyecto asignacionProyecto = (AsignacionProyecto) o;
        return codigoProyecto.equals(asignacionProyecto.codigoProyecto);
    }
}
