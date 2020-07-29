package com.sistemaGestion.dtos;

public class TareaDTO {

    private Integer codigoTarea;
    private String nombreTarea;
    private Integer codigoProyecto;
    private String nombreProyecto;
    private Integer progresoTarea;
    private String estado;

    public TareaDTO() {
    }

    public TareaDTO(Integer codigoTarea, String nombreTarea, Integer codigoProyecto, String nombreProyecto, Integer progresoTarea, String estado) {
        this.codigoTarea = codigoTarea;
        this.nombreTarea = nombreTarea;
        this.codigoProyecto = codigoProyecto;
        this.nombreProyecto = nombreProyecto;
        this.progresoTarea = progresoTarea;
        this.estado = estado;
    }

    public Integer getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(Integer codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Integer getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(Integer codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getProgresoTarea() {
        return progresoTarea;
    }

    public void setProgresoTarea(Integer progresoTarea) {
        this.progresoTarea = progresoTarea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
