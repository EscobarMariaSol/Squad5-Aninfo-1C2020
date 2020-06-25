package com.sistemaGestion.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.Consumer;

@Entity
@Table(indexes = { @Index(columnList = "legajo", unique = true) })
public class Empleado {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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
    private EmpleadoRol rol;

    @Column
    private String contrato;

    @Column
    private Seniority seniority;

    public Empleado(){

    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public EmpleadoRol getRol() {
        return rol;
    }

    public void setRol(EmpleadoRol rol) {
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
        this.dni = builder.dni;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.legajo = builder.legajo;
        this.rol = builder.rol;
        this.contrato = builder.contrato;
    }

    public void setSeniority(String seniority) {
        if (seniority.equalsIgnoreCase("junior")) {
            this.seniority = Seniority.Junior;
        }
        else if (seniority.equalsIgnoreCase("senior")) {
            this.seniority = Seniority.Senior;
        }
        else if (seniority.equalsIgnoreCase("senior")) {
            this.seniority = Seniority.SemiSenior;
        }
    }

    public String getSeniority() {
        return this.seniority.name();
    }

    public static class Builder {

        private Long id;
        private String nombre;
        private String apellido;
        private String dni;
        private String legajo;
        private LocalDate fechaNacimiento;
        private EmpleadoRol rol;
        private String contrato;

        public Builder conId(Long id) {
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

        public Builder conLegajo(String legajo) {
            this.legajo = legajo;
            return this;
        }

        public Builder conRol(EmpleadoRol rol) {
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

    public Boolean esLiderDeRecursosHumanos() {
        return this.rol.equals(EmpleadoRol.LIDER_RRHH);
    }

    @Override
    public String toString() {
        return String.format(
                "nombre: " + nombre +
                        ", apellido: " + apellido +
                        ", dni: " + dni +
                        ", fechaNacimiento: " + fechaNacimiento.toString()
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
                fechaNacimiento.equals(empleado.fechaNacimiento);
    }

}