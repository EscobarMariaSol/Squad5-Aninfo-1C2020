package com.sistemaGestion.model;

public class HorasCargadas {

    String fecha;

    int horas;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        this.fecha = fecha;
        this.horas = horas;
    }

}
