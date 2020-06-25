package com.sistemaGestion.assets;

import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.EmpleadoRol;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmpleadoFactory {

    public static Empleado crearEmpleado(Long id) {
        if (id.equals(Constants.EMPLEADO_ID_1)) {
            return new Empleado.Builder().con(empleadoData -> {
                empleadoData.conId(id);
                empleadoData.conNombre(Constants.EMPLEADO_NOMBRE_1);
                empleadoData.conApellido(Constants.EMPLEADO_APELLIDO_1);
                empleadoData.conDni(Constants.EMPLEADO_DNI_1);
                empleadoData.conFechaNacimiento(Constants.EMPLEADO_FECHA_NACIMIENTO_1);
                empleadoData.conLegajo(Constants.EMPLEADO_LEGAJO_1);
                empleadoData.conRol(Constants.EMPLEADO_ROL_1);
                empleadoData.conContrato(Constants.EMPLEADO_CONTRATO_1);
            }).build();
        } else if (id.equals(Constants.EMPLEADO_ID_2)) {
            return new Empleado.Builder().con(empleadoData -> {
                empleadoData.conId(id);
                empleadoData.conNombre(Constants.EMPLEADO_NOMBRE_2);
                empleadoData.conApellido(Constants.EMPLEADO_APELLIDO_2);
                empleadoData.conDni(Constants.EMPLEADO_DNI_2);
                empleadoData.conFechaNacimiento(Constants.EMPLEADO_FECHA_NACIMIENTO_2);
                empleadoData.conLegajo(Constants.EMPLEADO_LEGAJO_2);
                empleadoData.conRol(Constants.EMPLEADO_ROL_2);
                empleadoData.conContrato(Constants.EMPLEADO_CONTRATO_2);
            }).build();
        } else {
            return new Empleado.Builder().con(empleadoData -> {
                empleadoData.conId(id);
                empleadoData.conNombre(Constants.EMPLEADO_NOMBRE_3);
                empleadoData.conApellido(Constants.EMPLEADO_APELLIDO_3);
                empleadoData.conDni(Constants.EMPLEADO_DNI_3);
                empleadoData.conFechaNacimiento(Constants.EMPLEADO_FECHA_NACIMIENTO_3);
                empleadoData.conLegajo(Constants.EMPLEADO_LEGAJO_3);
                empleadoData.conRol(Constants.EMPLEADO_ROL_3);
                empleadoData.conContrato(Constants.EMPLEADO_CONTRATO_3);
            }).build();
        }
    }

    public static Empleado crearLiderDeRecursosHumanos() {
        return new Empleado.Builder().con(empleadoData -> {
            empleadoData.conId(Constants.LIDER_RRHH_ID);
            empleadoData.conNombre(Constants.LIDER_RRHH_NOMBRE);
            empleadoData.conApellido(Constants.LIDER_RRHH_APELLIDO);
            empleadoData.conDni(Constants.LIDER_RRHH_DNI);
            empleadoData.conFechaNacimiento(Constants.LIDER_RRHH_FECHA_NACIMIENTO);
            empleadoData.conLegajo(Constants.LIDER_RRHH_LEGAJO);
            empleadoData.conRol(Constants.LIDER_RRHH_ROL);
            empleadoData.conContrato(Constants.LIDER_RRHH_CONTRATO);
        }).build();
    }

    public static Empleado crearEmpleado(Map<String, String> atributosEmpleado) {
        return new Empleado.Builder().con(empleadoData -> {
            empleadoData.conId(Long.valueOf(1));
            empleadoData.conNombre(atributosEmpleado.get("nombre"));
            empleadoData.conApellido(atributosEmpleado.get("apellido"));
            empleadoData.conDni(atributosEmpleado.get("dni"));
            LocalDate fechaDeNacimiento = LocalDate.parse(atributosEmpleado.get("fechaDeNacimiento"));
            empleadoData.conFechaNacimiento(fechaDeNacimiento);
            empleadoData.conRol(EmpleadoRol.valueOf(atributosEmpleado.get("rol")));
            empleadoData.conContrato(atributosEmpleado.get("contrato"));
        }).build();
    }
}
