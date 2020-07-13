package com.sistemaGestion.dtos;

import com.sistemaGestion.model.*;

import java.time.LocalDate;
import java.util.function.Consumer;

public class PerfilEmpleadoDTO {

    private String legajo;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private EmpleadoRol rol;
    private String contrato;
    private Seniority seniority;
    private Boolean activo;

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public EmpleadoRol getRol() {
        return rol;
    }

    public String getContrato() {
        return contrato;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public Boolean getActivo() {
        return activo;
    }

    public PerfilEmpleadoDTO(PerfilEmpleadoDTO.Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.dni = builder.dni;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.legajo = builder.legajo;
        this.rol = builder.rol;
        this.contrato = builder.contrato;
        this.activo = builder.activo;
    }

    public static class Builder {

        private String nombre;
        private String apellido;
        private String dni;
        private String legajo;
        private LocalDate fechaNacimiento;
        private LocalDate fechaIngreso;
        private EmpleadoRol rol;
        private Seniority seniority;
        private String contrato;
        private Boolean activo;

        public PerfilEmpleadoDTO.Builder conNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conApellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conDni(String dni) {
            this.dni = dni;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conLegajo(String legajo) {
            this.legajo = legajo;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conRol(EmpleadoRol rol) {
            this.rol = rol;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conSeniority(Seniority seniority) {
            this.seniority = seniority;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conContrato(String contrato) {
            this.contrato = contrato;
            return this;
        }

        public PerfilEmpleadoDTO.Builder conActivo(Boolean activo) {
            this.activo = activo;
            return this;
        }

        public PerfilEmpleadoDTO.Builder con(Consumer<PerfilEmpleadoDTO.Builder> function) {
            function.accept(this);
            return this;
        }

        public PerfilEmpleadoDTO build() {
            return new PerfilEmpleadoDTO(this);
        }

    }

    public Empleado convertirAEmpleadoModelo(Empleado empleado) {
        return new Empleado.Builder().con(empleadoData -> {
            empleadoData.conNombre(this.nombre);
            empleadoData.conApellido(this.apellido);
            empleadoData.conDni(this.dni);
            empleadoData.conFechaNacimiento(this.fechaNacimiento);
            empleadoData.conFechaIngreso(this.fechaIngreso);
            empleadoData.conLegajo(this.legajo);
            empleadoData.conRol(this.rol);
            empleadoData.conContrato(this.contrato);
            empleadoData.conSeniority(this.seniority);
            empleadoData.conActivo(this.activo);
            empleadoData.conProyectos(empleado.getAsignacionProyectos());
            empleadoData.conHorasCargadas(empleado.getHorasCargadas());
        }).build();
    }

}
