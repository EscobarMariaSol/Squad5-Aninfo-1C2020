package com.sistemaGestion.model;

import com.sistemaGestion.model.enums.Actividad;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CargaDeHoras {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tareaId;

    @Column
    private String proyectoId;

    @Column
    private String legajo;

    @Column
    private LocalDate fecha;

    @Column
    private Float horasTrabajadas;

    @Column
    private Actividad actividad;

    public CargaDeHoras() {

    }

    public CargaDeHoras(Long id, String tareaId, String proyectoId, String legajo, LocalDate fecha, Float horasTrabajadas, Actividad actividad) {
        this.id = id;
        this.tareaId = tareaId;
        this.proyectoId = proyectoId;
        this.legajo = legajo;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
        this.actividad = actividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Float horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return String.format(
                "tareaId: " + tareaId +
                        ", proyectoId: " + proyectoId +
                        ", fecha: " + fecha.toString() +
                        ", horasCargadas: " + horasTrabajadas +
                        ", legajo: " + legajo
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
                tareaId.equals(cargaDeHoras.getTareaId()) &&
                legajo.equals(cargaDeHoras.getLegajo());
    }

}
