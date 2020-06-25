# language: es

Característica: mostrar tipo de contrato de un empleado

  Escenario: como Lider de Recursos Humanos, quiero consultar el tipo de contrato de un empleado,
    para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 empleado registrado
    Y tiene un tipo de contrato asignado
    Cuando consulto el tipo de contrato de un empleado registrado en el sistema
    Entonces se muestra el tipo de contrato asignado al empleado.

  Escenario: como Lider de Recursos Humanos, quiero consultar los empleados asignados a un proyecto,
  para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y no hay empleados registrados
    Cuando consulto el tipo de contrato de un empleado
    Entonces se muestra un mensaje indicando que no existe tal empleado.

  Escenario: como Lider de Recursos Humanos, quiero consultar el tipo de contrato de un empleado,
  para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 empleado registrado
    Y no tiene un tipo de contrato asignado
    Cuando consulto el tipo de contrato de un empleado registrado en el sistema
    Entonces se muestra un mensaje indicando que el empleado no tiene un tipo de contrato asignado.