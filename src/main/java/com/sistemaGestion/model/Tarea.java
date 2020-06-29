package com.sistemaGestion.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Código identificador de la tarea que viene del Módulo de Proyectos.
     * */
    @Column
    private String codigo;

    @ManyToOne
    private Proyecto proyecto;

}
