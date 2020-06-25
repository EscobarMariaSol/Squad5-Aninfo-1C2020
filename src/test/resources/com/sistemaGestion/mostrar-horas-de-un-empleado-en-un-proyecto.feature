# language: es

Característica: mostrar horas trabajadas por un empleado en un proyecto

  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Y hay 1 empleado asignado a un proyecto
    Cuando consulto las horas trabajadas por un empleado en un proyecto
    Entonces obtengo la información detallada del empleado y sus horas de trabajo.

  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Y no hay empleados asignados a los proyectos
    Cuando consulto las horas trabajadas por un empleado en un proyecto
    Entonces obtengo un mensaje que me indica que el empleado no tiene horas trabajadas en el proyecto.
