package com.sistemaGestion.model;

import java.time.LocalDate;

public class HorasCargadas {

    LocalDate fecha;

    int horas;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha);
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public HorasCargadas(){

    }

    public HorasCargadas(String fecha, int horas) {
        this.fecha = LocalDate.parse(fecha);
        this.horas = horas;
    }

}
