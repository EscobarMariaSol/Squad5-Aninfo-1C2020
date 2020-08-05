package com.sistemaGestion.model;

import com.sistemaGestion.exceptions.FechaInvalidaException;
import com.sistemaGestion.model.enums.EmpleadoRol;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AsignacionProyecto {

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAsignacion;

    @Column
    private String legajoEmpleado;

    @Column
    private Long codigoProyecto;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @Column
    private EmpleadoRol rolEmpleado;


    public AsignacionProyecto(){}

    public AsignacionProyecto(Long codigo, LocalDate fechaInicio, LocalDate fechaFin, EmpleadoRol rol) {
        this.codigoProyecto = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.rolEmpleado = rol;
    }

    public Long getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(Long codigo) {
        this.codigoProyecto = codigo;
    }

    public void setFechaInicio(LocalDate fecha) {
        this.fechaInicio = fecha;
    }

    public LocalDate getFechaInicio(){
        return this.fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if(fechaFin.isAfter(fechaInicio) || fechaFin.equals(fechaInicio)) {
            this.fechaFin = fechaFin;
        } else {
            throw new FechaInvalidaException("La fecha de fin no puede ser menor que la fecha de inicio");
        }
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

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public void setLegajoEmpleado(String legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public String getLegajoEmpleado() {
        return legajoEmpleado;
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
        return codigoProyecto.equals(asignacionProyecto.codigoProyecto) &&
                legajoEmpleado.equals(asignacionProyecto.legajoEmpleado);
    }
}
