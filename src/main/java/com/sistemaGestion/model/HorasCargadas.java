package com.sistemaGestion.model;

import java.time.LocalDate;

public class HorasCargadas {

    LocalDate fecha;

    Float horas;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha);
    }

    public Float getHoras() {
        return horas;
    }

    public void setHoras(Float horas) {
        this.horas = horas;
    }

    public HorasCargadas(){

    }

    public HorasCargadas(String fecha, Float horas) {
        this.fecha = LocalDate.parse(fecha);
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HorasCargadas horasCargadas = (HorasCargadas) o;
        return (this.horas == horasCargadas.getHoras() &&
                this.fecha.equals(horasCargadas.getFecha()));
    }
}
