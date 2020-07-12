package com.sistemaGestion.model;

public class HorasTrabajadas {

    public String legajo;
    public Integer cantidadDeHorasTrabajadas;
    public String nombreProyecto;
    public String tipoContrato;

    public HorasTrabajadas(String legajo, Integer cantidadDeHorasTrabajadas, String nombreProyecto, String tipoContrato) {
        this.legajo = legajo;
        this.cantidadDeHorasTrabajadas = cantidadDeHorasTrabajadas;
        this.nombreProyecto = nombreProyecto;
        this.tipoContrato = tipoContrato;
    }
}
