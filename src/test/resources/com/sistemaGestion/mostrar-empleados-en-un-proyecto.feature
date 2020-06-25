# language: es

Característica: mostrar empleados en un proyecto
  Escenario: como Lider de Recursos Humanos, quiero ver los empleados asignados a un proyecto,
    para así generar el reporte correspondiente.
    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Y hay empleados asignados a dichos proyectos
    Cuando consulto los empleados asignados a un proyecto
    Entonces obtengo un listado de los empleados asignados a dicho proyecto.

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Y no hay empleados asignados a los proyectos
    Cuando consulto los empleados asignados a un proyecto
    Entonces obtengo un listado vacio.

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y no hay proyectos en curso
    Y no hay empleados asignados a los proyectos
    Cuando consulto los empleados asignados a un proyecto
    Entonces obtengo un mensaje indicandome que no puedo consultar empleados dado que no existe el proyecto.