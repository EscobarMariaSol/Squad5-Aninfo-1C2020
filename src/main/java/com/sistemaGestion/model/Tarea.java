package com.sistemaGestion.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @EmbeddedId
    private TareaId id;

    @ManyToOne
    private Empleado empleado;

}
