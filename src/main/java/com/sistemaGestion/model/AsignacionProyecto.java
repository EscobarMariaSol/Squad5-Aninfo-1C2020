package com.sistemaGestion.model;

import javax.persistence.*;
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
    private String fechaInicio;

    @Column
    private String fechaFin;

    @Column
    private String rolEmpleado;


    public AsignacionProyecto(){}

    public AsignacionProyecto(String codigo, String fechaInicio, String fechaFin, String rol) {
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

    public void setFechaInicio(String fecha) {
        this.fechaInicio = fecha;
    }

    public String getFechaInicio(){
        return this.fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin(){
        return this.fechaFin;
    }

    public void setRolEmpleado(String rol) {
        this.rolEmpleado = rol;
    }

    public String getRolEmpleado() {
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
