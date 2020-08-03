package com.sistemaGestion.model;

import com.sistemaGestion.model.enums.Actividad;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CargaDeHoras {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Column
    public Actividad actividad;

    @Column
    public String legajo;

    @Column
    public Long tareaId;

    @Column
    public Long proyectoId;

    @Column
    public LocalDate fecha;

    @Column
    public Float horasTrabajadas;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTareaId() {
        return tareaId;
    }

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
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

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getLegajo() {
        return this.legajo;
    }

    public CargaDeHoras() {

    }
    public CargaDeHoras(Actividad actividad, Long tareaId, Long proyectoId, LocalDate fecha, Float horas, String legajo) {
        this.actividad = actividad;
        this.tareaId = tareaId;
        this.proyectoId = proyectoId;
        this.fecha =fecha;
        this.horasTrabajadas = horas;
        this.legajo = legajo;
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
                legajo.equals(cargaDeHoras.getLegajo()) &&
                actividad.equals(cargaDeHoras.getActividad());
    }



}
