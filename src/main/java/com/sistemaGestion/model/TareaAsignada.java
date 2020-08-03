package com.sistemaGestion.model;

public class TareaAsignada {

    public String id;

    public String responsable;

    public String prioridad;

    public String estado;

    public Integer iteracion;

    public String nombre;

    public String descripcion;

    public String fechaDeInicio;

    public String fechaDeFinalizacion;

    public TareaAsignada() {

    }

    public TareaAsignada(String id, String responsable, String prioridad, String estado, Integer iteracion, String nombre, String descripcion, String fechaDeInicio, String fechaDeFinalizacion) {
        this.id = id;
        this.responsable = responsable;
        this.prioridad = prioridad;
        this.estado = estado;
        this.iteracion = iteracion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIteracion() {
        return iteracion;
    }

    public void setIteracion(Integer iteracion) {
        this.iteracion = iteracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(String fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getFechaDeFinalizacion() {
        return fechaDeFinalizacion;
    }

    public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }

}
