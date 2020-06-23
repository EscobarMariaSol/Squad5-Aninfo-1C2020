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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Empleado(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
    }

    public static class Builder {

        private String id;
        private String nombre;
        private String apellido;
        private String dni;
        private LocalDate fechaNacimiento;
        private String rol;
        private String contrato;

        public Builder conId(String id) {
            this.id = id;
            return this;
        }

        public Builder conNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder conApellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder conDni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder conFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder conRol(String rol) {
            this.rol = rol;
            return this;
        }

        public Builder conContrato(String contrato) {
            this.contrato = contrato;
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
                "nombre: " + nombre +
                "apellido: " + apellido +
                "dni: " + dni +
                "fechaNacimiento: " + fechaNacimiento.toString() +
                "rol: " + rol +
                "contrato: " + contrato
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
        return nombre.equals(empleado.nombre) &&
                apellido.equals(empleado.apellido) &&
                dni.equals(empleado.dni) &&
                fechaNacimiento.equals(empleado.fechaNacimiento) &&
                rol.equals(empleado.rol) &&
                contrato.equals(empleado.contrato);
    }

}
