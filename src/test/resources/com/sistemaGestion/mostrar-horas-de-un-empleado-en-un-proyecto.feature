# language: es

Característica: mostrar horas trabajadas por un empleado en un proyecto

  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y tengo un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR | true   |

    Y este empleado ha cargado las horas trabajadas en las siguientes tareas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-09-19             | 3               |
      | 456         | 456          | 2020-09-19             | 4               |
      | 789         | 123          | 1979-09-20             | 2               |

    Cuando consulto las horas trabajadas por el empleado en el proyecto cuyo id es 456

    Entonces obtengo la siguiente informacion
      | legajo     | cantidadDeHorasTrabajadas   | nombreDeProyecto      | tipoDeContrato |
      | 5          | 7                           | 456                   | full-time      |

  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Y no hay empleados asignados a los proyectos
    Cuando consulto las horas trabajadas por un empleado en un proyecto
    Entonces obtengo un mensaje que me indica que el empleado no tiene horas trabajadas en el proyecto.
