package com.sistemaGestion.model;

import java.time.LocalDate;

public class HorasCargadas {

    LocalDate fecha;

    int horas;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public HorasCargadas(LocalDate fecha, int horas) {
        this.fecha = fecha;
        this.horas = horas;
    }

}
