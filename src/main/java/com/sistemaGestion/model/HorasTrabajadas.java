package com.sistemaGestion.model;

import com.sistemaGestion.model.enums.EmpleadoContrato;

public class HorasTrabajadas {

    public String legajo;
    public Integer cantidadDeHorasTrabajadas;
    public String nombreProyecto;
    public EmpleadoContrato tipoContrato;

    public HorasTrabajadas(String legajo, Integer cantidadDeHorasTrabajadas, String nombreProyecto, EmpleadoContrato tipoContrato) {
        this.legajo = legajo;
        this.cantidadDeHorasTrabajadas = cantidadDeHorasTrabajadas;
        this.nombreProyecto = nombreProyecto;
        this.tipoContrato = tipoContrato;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HorasTrabajadas horasTrabajadas = (HorasTrabajadas) o;
        return this.legajo.equals(horasTrabajadas.legajo) &&
                this.cantidadDeHorasTrabajadas.equals(horasTrabajadas.cantidadDeHorasTrabajadas) &&
                this.nombreProyecto.equals(horasTrabajadas.nombreProyecto) &&
                this.tipoContrato.equals(horasTrabajadas.tipoContrato);
    }

}
