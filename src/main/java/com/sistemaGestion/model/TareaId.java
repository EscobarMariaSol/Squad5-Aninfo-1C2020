package com.sistemaGestion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TareaId implements Serializable {

    @Column
    private String codigoTarea;

    @Column
    private String codigoProyecto;

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public TareaId() {

    }

    public TareaId(String codigoTarea, String codigoProyecto) {
        this.codigoTarea = codigoTarea;
        this.codigoProyecto = codigoProyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaId that = (TareaId) o;
        return codigoTarea.equals(that.codigoTarea) &&
                codigoProyecto.equals(that.codigoProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoTarea, codigoProyecto);
    }
    
}
