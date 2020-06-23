package com.sistemaGestion.assets;

import java.time.LocalDate;

public class Constants {

    //TODO ver roles
    // Roles
    public final static String DESARROLLO = "Desarrollo";
    public final static String LIDER = "Líder";
    public final static String LIDER_RRHH = "Líder RRHH";

    // Contratos
    public final static String FULL_TIME = "Full-time";
    public final static String PART_TIME = "Part-time";
    public final static String SOPORTE = "Soporte";

    // Empleados
    public final static String EMPLEADO_ID_1 = "1";
    public final static String EMPLEADO_NOMBRE_1 = "Jane";
    public final static String EMPLEADO_APELLIDO_1 = "Doe";
    public final static String EMPLEADO_DNI_1 = "12345678";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_1 =
            LocalDate.of(1990, 6, 22);
    public final static String EMPLEADO_LEGAJO_1 = "123";
    public final static String EMPLEADO_ROL_1 = DESARROLLO;
    public final static String EMPLEADO_CONTRATO_1 = FULL_TIME;

    public final static String EMPLEADO_ID_2 = "2";
    public final static String EMPLEADO_NOMBRE_2 = "Jon";
    public final static String EMPLEADO_APELLIDO_2 = "Smith";
    public final static String EMPLEADO_DNI_2 = "6354728";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_2 =
            LocalDate.of(1995, 1, 2);
    public final static String EMPLEADO_LEGAJO_2 = "261";
    public final static String EMPLEADO_ROL_2 = DESARROLLO;
    public final static String EMPLEADO_CONTRATO_2 = PART_TIME;

    public final static String EMPLEADO_ID_3 = "3";
    public final static String EMPLEADO_NOMBRE_3 = "Hermione";
    public final static String EMPLEADO_APELLIDO_3 = "Granger";
    public final static String EMPLEADO_DNI_3 = "7362578";
    public final static LocalDate EMPLEADO_FECHA_NACIMIENTO_3 =
            LocalDate.of(1987, 10, 30);
    public final static String EMPLEADO_LEGAJO_3 = "156";
    public final static String EMPLEADO_ROL_3 = DESARROLLO;
    public final static String EMPLEADO_CONTRATO_3 = SOPORTE;

    // Líder de Recursos Humanos
    public final static String LIDER_RRHH_ID = "4";
    public final static String LIDER_RRHH_NOMBRE = "Harry";
    public final static String LIDER_RRHH_APELLIDO = "Potter";
    public final static String LIDER_RRHH_DNI = "8219737";
    public final static LocalDate LIDER_RRHH_FECHA_NACIMIENTO =
            LocalDate.of(1985, 4, 9);
    public final static String LIDER_RRHH_LEGAJO = "548";
    public final static String LIDER_RRHH_ROL = LIDER_RRHH;
    public final static String LIDER_RRHH_CONTRATO = FULL_TIME;


}
