package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.EmpleadoContrato;

public class CargaDeHorasDTO {

    public String legajo;
    public Float cantidadDeHoras;

    public CargaDeHorasDTO(String legajo, Float horasTrabajadas) {
        this.legajo = legajo;
        this.cantidadDeHoras = horasTrabajadas;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CargaDeHorasDTO cargaDeHorasDTO = (CargaDeHorasDTO) o;
        return this.legajo.equals(cargaDeHorasDTO.legajo) &&
                this.cantidadDeHoras.equals(cargaDeHorasDTO.cantidadDeHoras);
    }

}
