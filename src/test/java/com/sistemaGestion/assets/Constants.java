package com.sistemaGestion.assets;

import com.sistemaGestion.model.enums.EmpleadoContrato;
import com.sistemaGestion.model.enums.EmpleadoRol;

import java.time.LocalDate;

public class Constants {

    // Empleados
    public final static Long EMPLEADO_ID_1 = 1L;
    public final static String EMPLEADO_NOMBRE_1 = "Jane";
    public final static String EMPLEADO_APELLIDO_1 = "Doe";
    public final static String EMPLEADO_DNI_1 = "12345678";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_1 =
            LocalDate.of(1990, 6, 22);
    public final static String EMPLEADO_LEGAJO_1 = "123";
    public final static EmpleadoRol EMPLEADO_ROL_1 = EmpleadoRol.DESARROLLADOR;
    public final static EmpleadoContrato EMPLEADO_CONTRATO_1 = EmpleadoContrato.FULL_TIME;

    public final static Long EMPLEADO_ID_2 = 2L;
    public final static String EMPLEADO_NOMBRE_2 = "Jon";
    public final static String EMPLEADO_APELLIDO_2 = "Smith";
    public final static String EMPLEADO_DNI_2 = "6354728";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_2 =
            LocalDate.of(1995, 1, 2);
    public final static String EMPLEADO_LEGAJO_2 = "261";
    public final static EmpleadoRol EMPLEADO_ROL_2 = EmpleadoRol.DESARROLLADOR;
    public final static EmpleadoContrato EMPLEADO_CONTRATO_2 = EmpleadoContrato.PART_TIME;

    public final static Long EMPLEADO_ID_3 = 3L;
    public final static String EMPLEADO_NOMBRE_3 = "Hermione";
    public final static String EMPLEADO_APELLIDO_3 = "Granger";
    public final static String EMPLEADO_DNI_3 = "7362578";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_3 =
            LocalDate.of(1987, 10, 30);
    public final static String EMPLEADO_LEGAJO_3 = "156";
    public final static EmpleadoRol EMPLEADO_ROL_3 = EmpleadoRol.DESARROLLADOR;
    public final static EmpleadoContrato EMPLEADO_CONTRATO_3 = EmpleadoContrato.FULL_TIME;

    // Líder de Recursos Humanos
    public final static Long LIDER_RRHH_ID = 4L;
    public final static String LIDER_RRHH_NOMBRE = "Harry";
    public final static String LIDER_RRHH_APELLIDO = "Potter";
    public final static String LIDER_RRHH_DNI = "8219737";
    public final static LocalDate LIDER_RRHH_FECHA_NACIMIENTO =
            LocalDate.of(1985, 4, 9);
    public final static String LIDER_RRHH_LEGAJO = "548";
    public final static EmpleadoRol LIDER_RRHH_ROL = EmpleadoRol.LIDER_RRHH;
    public final static EmpleadoContrato LIDER_RRHH_CONTRATO = EmpleadoContrato.FULL_TIME;

    // Desarrollador
    public static final Long DESARROLLADOR_ID = 4L;
    public static final String DESARROLLADOR_NOMBRE = "Felipe";
    public static final String DESARROLLADOR_APELLIDO = "Codeo";
    public static final String DESARROLLADOR_DNI = "34567891";
    public static final LocalDate DESARROLLADOR_FECHA_NACIMIENTO =
            LocalDate.of(1990, 11, 23);
    public static final EmpleadoRol DESARROLLADOR_ROL = EmpleadoRol.DESARROLLADOR;
    public static final EmpleadoContrato DESARROLLADOR_CONTRATO = EmpleadoContrato.FULL_TIME;

    // Líder de proyecto
    public final static Long LIDER_PROYECTO_ID = 5L;
    public final static String LIDER_PROYECTO_NOMBRE = "Maximiliano";
    public final static String LIDER_PROYECTO_APELLIDO = "Gantt";
    public final static String LIDER_PROYECTO_DNI = "2219737";
    public final static LocalDate LIDER_PROYECTO_FECHA_NACIMIENTO =
            LocalDate.of(1975, 4, 9);
    public final static String LIDER_PROYECTO_LEGAJO = "890";
    public final static EmpleadoRol LIDER_PROYECTO_ROL = EmpleadoRol.LIDER_PROYECTO;
    public final static EmpleadoContrato LIDER_PROYECTO_CONTRATO = EmpleadoContrato.FULL_TIME;

}
