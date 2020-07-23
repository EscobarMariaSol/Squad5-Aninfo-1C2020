package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.EmpleadoContrato;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReporteDeHorasDTO {

    private Map<LocalDate, Integer> horas;
    private Integer horasTotales;
    private EmpleadoContrato contrato;
    private String tareaId;
    private String proyectoid;

    public ReporteDeHorasDTO(EmpleadoContrato contrato){
        this.horas = new HashMap<LocalDate, Integer>();
        this.horasTotales = 0;
        this.contrato = contrato;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }

    public EmpleadoContrato getContrato() {
        return contrato;
    }

    public void setContrato(EmpleadoContrato contrato) {
        this.contrato = contrato;
    }

    public String getProyectoid() {
        return proyectoid;
    }

    public void setProyectoid(String proyectoid) {
        this.proyectoid = proyectoid;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public Map<LocalDate, Integer> getHoras() {
        return horas;
    }

    public void setHoras(Map<LocalDate, Integer> horas) {
        this.horas = horas;
    }

    public void addHoras(LocalDate fecha, Integer horas) {
        if (this.horas.containsKey(fecha)) {
            Integer horasIniciales = this.horas.get(fecha);
            this.horas.replace(fecha, (horasIniciales + horas));
        } else {
            this.horas.put(fecha, horas);
        }
        this.horasTotales += horas;
    }

    @Override
    public String toString() {
        return "ReporteDeHorasDTO{" +
                "Horas: " + horas.toString() +
                ", Horas Totales: " + horasTotales +
                ", Contrato: " + contrato +
                ", Tarea: '" + tareaId + '\'' +
                ", Proyectoid: '" + proyectoid + '\'' +
                '}';
    }
}
