package com.sistemaGestion.model;

import com.sun.org.apache.bcel.internal.generic.DADD;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
public class AsignacionProyecto {

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Id
    private String codigo;

    @Column
    private String fechaInicio;
    @Column
    private String fechaFin;

    @Column
    private String rolEmpleado;

    @OneToMany
    private Set<Tarea> tareas;

    public AsignacionProyecto(){}

    public AsignacionProyecto(String codigo, String fechaInicio, String fechaFin, String rol) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.rolEmpleado = rol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
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
        return codigo.equals(asignacionProyecto.codigo);
    }
}
