package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.model.enums.EmpleadoContrato;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReporteDeHorasDTO {

    private Map<LocalDate, Float> horas;
    private Float horasTotales;
    private EmpleadoContrato contrato;
    private String tareaId;
    private String proyectoid;
    private Actividad actividad;
    private LocalDate fecha;
    private Float cantidadHoras;

    public ReporteDeHorasDTO(){

    }

    public ReporteDeHorasDTO(EmpleadoContrato contrato){
        this.horas = new HashMap<LocalDate, Float>();
        this.horasTotales = 0F;
        this.contrato = contrato;
    }

    public void setHorasTotales(Float horasTotales) {
        this.horasTotales = horasTotales;
    }

    public EmpleadoContrato getContrato() {
        return contrato;
    }

    public void setContrato(EmpleadoContrato contrato) {
        this.contrato = contrato;
    }

    public String getProyectoid() {
        return  proyectoid;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setProyectoid(String proyectoid) {
        this.proyectoid = proyectoid;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Float cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public Float getHorasTotales() {
        return horasTotales;
    }

    public Map<LocalDate, Float> getHoras() {
        return horas;
    }

    public void setHoras(Map<LocalDate, Float> horas) {
        this.horas = horas;
    }

    public void addHoras(LocalDate fecha, Float horas) {
        if (this.horas.containsKey(fecha)) {
            Float horasIniciales = this.horas.get(fecha);
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
                ", Proyecto: '" + proyectoid + '\'' +
                '}';
    }
}
