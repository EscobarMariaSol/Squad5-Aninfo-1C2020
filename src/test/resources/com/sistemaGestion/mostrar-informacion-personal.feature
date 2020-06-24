# language: es

Característica: mostrar informacion personal de un empleado

  Escenario: como Lider de Recursos Humanos, quiero consultar los empleados asignados a un proyecto,
    para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 proyecto registrado
    Y hay 1 empleado registrado en el proyecto
    Cuando consulto los empleados de dicho proyecto
    Entonces debo ver un listado con un empleado y la informacion asociada al mismo.

  Escenario: como Lider de Recursos Humanos, quiero consultar los empleados asignados a un proyecto,
    para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 proyecto registrado
    Y no hay empleados asignados al proyecto
    Cuando consulto los empleados de dicho proyecto
    Entonces obtengo un listado vacio.
