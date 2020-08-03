package com.sistemaGestion.model;

public class TareaAsignada {

    public String id;

    public String nombreTarea;

    public String nombreProyecto;

    public Float duracionEstimada;

    public String estado;


    public TareaAsignada(String id, String nombreTarea, String nombreProyecto, Float duracionEstimada, String estado) {
        this.id = id;
        this.nombreTarea = nombreTarea;
        this.nombreProyecto = nombreProyecto;
        this.duracionEstimada = duracionEstimada;
        this.estado = estado;
    }

    public TareaAsignada() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Float getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Float duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
