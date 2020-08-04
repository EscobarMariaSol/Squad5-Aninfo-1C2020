package com.sistemaGestion.dtos;

import com.sistemaGestion.model.enums.Actividad;
import com.sistemaGestion.model.enums.EmpleadoContrato;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReporteDeHorasDTO {

    private Actividad actividad;
    private Long tareaId;
    private Long proyectoId;
    private LocalDate fecha;
    private Float cantidadHoras;

<<<<<<< HEAD
    public ReporteDeHorasDTO() {
    }

    public ReporteDeHorasDTO(Actividad actividad, Long tareaId, Long proyectoId, LocalDate fecha, Float cantidadHoras){
        this.tareaId = tareaId;
        this.actividad = actividad;
        this.fecha = fecha;
        this.cantidadHoras = cantidadHoras;
        this.proyectoId = proyectoId;
=======
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
>>>>>>> 6693386a8fc494e6eaa8e9f941180aad35428e1a
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
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

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public Long getTareaId() {
        return tareaId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    @Override
    public String toString() {
        return "ReporteDeHorasDTO{" +
                "Fecha: " + fecha +
                ", Horas: " + cantidadHoras +
                ", Activida: '" + actividad + '\'' +
                ", Tarea: '" + tareaId + '\'' +
                ", Proyecto: '" + proyectoId +'\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReporteDeHorasDTO reporteDeHorasDTO = (ReporteDeHorasDTO) o;
        return this.actividad.equals(reporteDeHorasDTO.actividad) &&
                ((this.tareaId == null && reporteDeHorasDTO.tareaId == null)
                        || this.tareaId.equals(reporteDeHorasDTO.tareaId)) &&
                ((this.proyectoId == null && reporteDeHorasDTO.proyectoId == null)
                        || this.proyectoId.equals(reporteDeHorasDTO.proyectoId)) &&
                this.fecha.equals(reporteDeHorasDTO.fecha) &&
                this.cantidadHoras.equals(reporteDeHorasDTO.cantidadHoras);
    }
}
