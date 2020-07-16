package com.sistemaGestion.model;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Entity
public class Empleado {

    @Id
    private String legajo;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String dni;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private EmpleadoRol rol;

    @Column
    private String contrato;

    @Column
    private Seniority seniority;

    @Column
    private Boolean activo;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<AsignacionProyecto> asignacionProyectos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CargaDeHoras> horasCargadas;

    public Set<CargaDeHoras> getHorasCargadas() {
        return horasCargadas;
    }

    public void setHorasCargadas(Set<CargaDeHoras> horasCargadas) {
        this.horasCargadas = horasCargadas;
    }

    public Empleado(){
        this.asignacionProyectos = new HashSet<>();
    }

    public Empleado(String nombre) {
        this.nombre = nombre;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<AsignacionProyecto> getAsignacionProyectos() {
        return asignacionProyectos;
    }

    public void setAsignacionProyectos(Set<AsignacionProyecto> asignacionProyectos) {
        this.asignacionProyectos = asignacionProyectos;
    }

    public void setSeniority(String seniority) {
        if (seniority.equalsIgnoreCase("junior")) {
            this.seniority = Seniority.JUNIOR;
        }
        else if (seniority.equalsIgnoreCase("senior")) {
            this.seniority = Seniority.SENIOR;
        }
        else if (seniority.equalsIgnoreCase("senior")) {
            this.seniority = Seniority.SEMI_SENIOR;
        } else {
            this.seniority = Seniority.SIN_SENIORITY;
        }
    }

    public String getSeniority() {
        return this.seniority.name();
    }

    public Empleado(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.dni = builder.dni;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.legajo = builder.legajo;
        this.rol = builder.rol;
        this.contrato = builder.contrato;
        this.activo = builder.activo;
        this.horasCargadas = builder.horasCargadas;
        this.asignacionProyectos = builder.asignacionProyectos;
    }

    public void addProyecto(AsignacionProyecto asignacionProyecto) {
        this.asignacionProyectos.add(asignacionProyecto);
    }

    public boolean perteneceAProyecto(AsignacionProyecto asignacionProyecto) {
        for (AsignacionProyecto p: this.asignacionProyectos) {
            if (p.equals(asignacionProyecto))
                return true;
        }
        return false;
    }

    public void cargarHoras(CargaDeHoras cargaDeHoras) {
        horasCargadas.add(cargaDeHoras);
    }

    public Integer getHorasCargadas(String idTarea, String idProyecto, String fecha) {
        int cont = 0;
        for (CargaDeHoras cargaDeHoras : this.horasCargadas) {
            if (cargaDeHoras.fecha.equals(LocalDate.parse(fecha)) &
                    cargaDeHoras.tareaId.equals(idTarea) &
                    cargaDeHoras.proyectoId.equals(idProyecto))
                cont += cargaDeHoras.horasTrabajadas;
        }
        return cont;
    }


    public static class Builder {

        private String nombre;
        private String apellido;
        private String dni;
        private String legajo;
        private LocalDate fechaNacimiento;
        private EmpleadoRol rol;
        private String contrato;
        private Boolean activo;
        private Set<CargaDeHoras> horasCargadas;
        private Set<AsignacionProyecto> asignacionProyectos;

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

        public Builder conActivo(Boolean activo) {
            this.activo = activo;
            return this;
        }

        public Builder conProyectos(Set<AsignacionProyecto> asignacionProyectos) {
            this.asignacionProyectos = asignacionProyectos;
            return this;
        }

        public Builder conHorasCargadas(Set<CargaDeHoras> cargaDeHoras) {
            this.horasCargadas = horasCargadas;
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