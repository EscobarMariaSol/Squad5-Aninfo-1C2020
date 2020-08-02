package com.sistemaGestion.dtos;

public class CargaDeHorasDTO {

    public String legajo;

    public Float horasTrabajadas;

    public CargaDeHorasDTO(String legajo, Float horasTrabajadas) {
        this.legajo = legajo;
        this.horasTrabajadas = horasTrabajadas;
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
                this.horasTrabajadas.equals(cargaDeHorasDTO.horasTrabajadas);
    }

}
