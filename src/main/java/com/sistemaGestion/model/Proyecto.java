package com.sistemaGestion.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Proyecto {

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Id
    private String codigo;

    @OneToMany
    private Set<Tarea> tareas;

    @ManyToOne
    private Empleado empleado;

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

}
