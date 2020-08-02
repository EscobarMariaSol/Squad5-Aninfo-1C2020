package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.EmpleadoContrato;

public class CargaDeHorasDTO {

    public String legajo;
    public Float horasTrabajadas;
    public Long codigoProyecto;
    public EmpleadoContrato tipoContrato;

    public CargaDeHorasDTO(String legajo, Float horasTrabajadas, Long codigoProyecto, EmpleadoContrato tipoContrato) {
        this.legajo = legajo;
        this.horasTrabajadas = horasTrabajadas;
        this.codigoProyecto = codigoProyecto;
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

        CargaDeHorasDTO cargaDeHorasDTO = (CargaDeHorasDTO) o;
        return this.legajo.equals(cargaDeHorasDTO.legajo) &&
                this.horasTrabajadas.equals(cargaDeHorasDTO.horasTrabajadas) &&
                this.codigoProyecto.equals(cargaDeHorasDTO.codigoProyecto) &&
                this.tipoContrato.equals(cargaDeHorasDTO.tipoContrato);
    }

}
