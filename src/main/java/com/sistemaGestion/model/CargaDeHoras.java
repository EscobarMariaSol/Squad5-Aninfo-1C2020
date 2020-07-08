package com.sistemaGestion.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class CargaDeHoras {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Column
    public  String tareaId;

    @Column
    public String proyectoId;

    @Column
    public LocalDate fecha;

    @Column
    public int horasTrabajadas;

    public CargaDeHoras() {

    }
    public CargaDeHoras(String tareaId, String proyectoId, LocalDate fecha, int horas) {
        this.tareaId = tareaId;
        this.proyectoId = proyectoId;
        this.fecha =fecha;
        this.horasTrabajadas = horas;
    }

}
