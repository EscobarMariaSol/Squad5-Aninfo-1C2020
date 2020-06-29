package com.sistemaGestion.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Código identificador del proyecto que viene del Módulo de Proyectos.
     * */
    @Column
    private String codigo;

    

}
