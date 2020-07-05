package com.sistemaGestion.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Proyecto {

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Id
    private String codigo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Tarea> tareas;

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

    public void cargarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public Proyecto() {

    }

    public Proyecto(String codigoProyecto) {
        this.codigo = codigoProyecto;
        this.tareas = new HashSet<>();
    }
}
