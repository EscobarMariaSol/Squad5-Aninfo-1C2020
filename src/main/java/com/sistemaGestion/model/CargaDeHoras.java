package com.sistemaGestion.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CargaDeHoras {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Column
    public  String tareaId;

    @Column
    public String proyectoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Column
    public LocalDate fecha;

    @Column
    public Integer horasTrabajadas;

    public CargaDeHoras() {

    }
    public CargaDeHoras(String tareaId, String proyectoId, LocalDate fecha, int horas) {
        this.tareaId = tareaId;
        this.proyectoId = proyectoId;
        this.fecha =fecha;
        this.horasTrabajadas = horas;
    }

    @Override
    public String toString() {
        return String.format(
                "tareaId: " + tareaId +
                        ", proyectoId: " + proyectoId +
                        ", fecha: " + fecha.toString() +
                        ", horasCargadas: " + horasTrabajadas
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CargaDeHoras cargaDeHoras = (CargaDeHoras) o;
        return proyectoId.equals(cargaDeHoras.getProyectoId()) &&
                fecha.equals(cargaDeHoras.getFecha()) &&
                horasTrabajadas.equals(cargaDeHoras.getHorasTrabajadas()) &&
                tareaId.equals(cargaDeHoras.getTareaId());
    }



}
