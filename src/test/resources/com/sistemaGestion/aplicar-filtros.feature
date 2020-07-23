# language: es

Característica: aplicar filtros para generar reportes

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
    para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-09-17             | 3               |
      | 123         | 456          | 2020-09-19             | 4               |
      | 789         | 123          | 1979-09-20             | 2               |

    Cuando consulto las horas trabajadas por el empleado con legajo '5' en el proyecto '456' aplicando los siguientes filtros
      | tareaId     | proyectoId   | fechaInicio     | fechaFin        |
      | 123         | 456          | 2020-09-17      | 2020-09-19      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | tareaId | proyectoId        |
      | 2020-09-17      | 3                         | 123     | 456               |
      | 2020-09-19      | 4                         | 123     | 456               |
    Y se me indica que la cantidad de horas totales es 7

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-09-17             | 3               |
      | 345         | 456          | 2020-09-19             | 6               |
      | 546         | 456          | 2020-09-17             | 4               |

    Cuando consulto las horas trabajadas por el empleado con legajo '5' en el proyecto '456' aplicando los filtros
      | tareaId     | proyectoId   | fechaInicio     | fechaFin        |
      |             | 456          | 2020-09-17      | 2020-09-19      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | tareaId | proyectoId        |
      | 2020-09-17      | 7                         |         | 456               |
      | 2020-09-19      | 6                         |         | 456               |
    Y se me indica que la cantidad de horas totales es 13
