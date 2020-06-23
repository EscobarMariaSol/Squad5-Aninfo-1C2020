package com.sistemaGestion.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.Consumer;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String dni;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private String legajo;

    @Column
    private String rol;

    @Column
    private String contrato;

    public Empleado(){

    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empleado(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
    }

    public static class Builder {

        private String id;
        private String nombre;

        public Builder conId(String id) {
            this.id = id;
            return this;
        }

        public Builder conNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder con(Consumer<Builder> function) {
            function.accept(this);
            return this;
        }

        public Empleado build() {
            return new Empleado(this);
        }

    }
//TODO Implementar
    public Boolean esLiderDeRecursosHumanos() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "nombre: " + nombre
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Empleado empleado = (Empleado) o;
        return nombre.equals(empleado.nombre);
    }

}
