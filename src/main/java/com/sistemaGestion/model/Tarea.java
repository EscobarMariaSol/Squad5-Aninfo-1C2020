package com.sistemaGestion.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Tarea {

    @EmbeddedId
    private TareaId id;

    public LocalDate fechaFinalizacion;

    public int horasTrabajadas;

    public String legajoEmpleado;

    public TareaId getId() {
        return id;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setId(TareaId id) {
        this.id = id;
    }

    public String getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public void setLegajoEmpleado(String legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }


    public Tarea(TareaId id, Empleado empleado) {
        this.id = id;
    }

    public Tarea() {

    }

    public void cargarHoras(HorasCargadas horasCargadas, String legajoEmpleado) {
        this.fechaFinalizacion = LocalDate.parse(horasCargadas.getFecha());
        this.horasTrabajadas = horasCargadas.getHoras();
        this.legajoEmpleado = legajoEmpleado;
    }
}
