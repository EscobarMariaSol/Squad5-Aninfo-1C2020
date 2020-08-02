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
      | actividad | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | PROYECTO  | 123         | 456          | 2020-08-01             | 3               |
      | PROYECTO  | 123         | 456          | 2020-08-02             | 4               |
      | PROYECTO  | 789         | 123          | 2020-08-01             | 2               |

    Cuando consulto las horas trabajadas por el empleado con legajo '5' en el proyecto '456' aplicando los siguientes filtros
      | tareaId     | proyectoId   | fechaInicio     | fechaFin        |
      | 123         | 456          | 2020-08-01      | 2020-09-19      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | tareaId | proyectoId        |
      | 2020-08-01      | 3                         | 123     | 456               |
      | 2020-08-02      | 4                         | 123     | 456               |
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
      | 123         | 456          | 2020-08-02             | 3               |
      | 345         | 456          | 2020-08-01             | 6               |
      | 546         | 456          | 2020-08-02             | 4               |

    Cuando consulto las horas trabajadas por el empleado con legajo '5' en el proyecto '456' aplicando los filtros
      | proyectoId   | fechaInicio     | fechaFin        |
      | 456          | 2020-08-01      | 2020-08-01      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | proyectoId        |
      | 2020-08-01      | 6                         | 456               |
    Y se me indica que la cantidad de horas totales es 6

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-08-02             | 3               |
      | 345         | 456          | 2020-08-01             | 6               |
      | 123         | 123          | 2020-08-01             | 4               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' aplicando los siguientes filtros
      | tareaId   | proyectoId   | fechaInicio     | fechaFin        |
      | 123       |              | 2020-08-02      | 2020-08-02      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | tareaId        |
      | 2020-08-02      | 3                         | 123            |
    Y se me indica que la cantidad de horas totales es 3

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-08-02             | 3               |
      | 345         | 456          | 2020-08-01             | 6               |
      | 345         | 456          | 2020-08-02             | 6               |
      | 123         | 123          | 2020-08-01             | 4               |

    Cuando consulto las horas trabajadas por el empleado con legajo '5' aplicando los siguientes filtros
      | tareaId     | proyectoId   | fechaInicio     | fechaFin        |
      |             |              | 2020-08-01      | 2020-08-01      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas |
      | 2020-08-01      | 10                         |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | 456          | 2020-08-02             | 3               |
      | 345         | 456          | 2020-08-01             | 6               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' aplicando los siguientes filtros
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      |             |              |                        |                 |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas |
      | 2020-08-02      | 3                         |
      | 2020-08-01      | 6                         |
    Y se me indica que la cantidad de horas totales es 9

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y no existe el empleado con legajo '33'
    Cuando consulto las horas trabajadas por el empleado con legajo '5' aplicando los siguientes filtros
      | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      |             |              |                        |                 |
    Entonces recibo un mensaje indicandome que no existe dicho empleado