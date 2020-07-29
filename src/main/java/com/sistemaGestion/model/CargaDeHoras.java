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
    private Long codigoTarea;

    @Column
    private Long codigoProyecto;

    @Column
    private String legajo;

    @Column
    private LocalDate fecha;

    @Column
    private Float cantidadhoras;

    @Column
    private Actividad actividad;

    public CargaDeHoras() {

    }

    public CargaDeHoras(Long codigoTarea, Long codigoProyecto, String legajo, LocalDate fecha, Float cantidadhoras, Actividad actividad) {
        this.codigoTarea = codigoTarea;
        this.codigoProyecto = codigoProyecto;
        this.legajo = legajo;
        this.fecha = fecha;
        this.cantidadhoras = cantidadhoras;
        this.actividad = actividad;
    }

    public CargaDeHoras(Long id, Long codigoTarea, Long codigoProyecto, String legajo, LocalDate fecha, Float cantidadhoras, Actividad actividad) {
        this.id = id;
        this.codigoTarea = codigoTarea;
        this.codigoProyecto = codigoProyecto;
        this.legajo = legajo;
        this.fecha = fecha;
        this.cantidadhoras = cantidadhoras;
        this.actividad = actividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(Long tareaId) {
        this.codigoTarea = tareaId;
    }

    public Long getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(Long proyectoId) {
        this.codigoProyecto = proyectoId;
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

    public Float getCantidadhoras() {
        return cantidadhoras;
    }

    public void setCantidadhoras(Float horasTrabajadas) {
        this.cantidadhoras = horasTrabajadas;
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
                "tareaId: " + codigoTarea +
                        ", proyectoId: " + codigoProyecto +
                        ", fecha: " + fecha.toString() +
                        ", horasCargadas: " + cantidadhoras +
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
        return codigoProyecto.equals(cargaDeHoras.getCodigoProyecto()) &&
                fecha.equals(cargaDeHoras.getFecha()) &&
                cantidadhoras.equals(cargaDeHoras.getCantidadhoras()) &&
                codigoTarea.equals(cargaDeHoras.getCodigoTarea()) &&
                legajo.equals(cargaDeHoras.getLegajo());
    }

}
