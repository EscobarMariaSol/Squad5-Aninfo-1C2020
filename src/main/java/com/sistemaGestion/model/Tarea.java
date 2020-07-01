package com.sistemaGestion.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @EmbeddedId
    private TareaId id;

    public TareaId getId() {
        return id;
    }

    public void setId(TareaId id) {
        this.id = id;
    }

    public Tarea(TareaId id, Empleado empleado) {
        this.id = id;
    }

    public Tarea() {

    }

}
