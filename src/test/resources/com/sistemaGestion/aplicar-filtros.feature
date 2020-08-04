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
      | TAREA     | 123         | 456          | 2020-08-01             | 3               |
      | TAREA     | 123         | 456          | 2020-08-02             | 4               |
      | TAREA     | 789         | 123          | 2020-08-01             | 2               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' en el proyecto '456' aplicando los siguientes filtros
      | actividad | tareaId     | proyectoId   | fechaInicio     | fechaFin        |
      | TAREA     | 123         | 456          | 2020-08-01      | 2020-09-19      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | tareaId | proyectoId | actividad |
      | 2020-08-01      | 3                         | 123     | 456        |   TAREA   |
      | 2020-08-02      | 4                         | 123     | 456        |   TAREA   |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | actividad | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | TAREA     | 123         | 456          | 2020-08-02             | 3               |
      | TAREA     | 345         | 456          | 2020-08-01             | 6               |
      | TAREA     | 546         | 456          | 2020-08-02             | 4               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' filtrando por proyecto y fechas
      | proyectoId   | fechaInicio     | fechaFin        |
      | 456          | 2020-08-01      | 2020-08-01      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | actividad | proyectoId | tareaId     |
      | 2020-08-01      | 6                         | TAREA     | 456        | 345         |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | actividad | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | TAREA     | 123         | 456          | 2020-08-02             | 3               |
      | TAREA     | 345         | 456          | 2020-08-01             | 6               |
      | TAREA     | 123         | 123          | 2020-08-01             | 4               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' filtrando por tarea y fechas
      | tareaId   | fechaInicio     | fechaFin        |
      | 123       | 2020-08-02      | 2020-08-02      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | actividad | proyectoId | tareaId     |
      | 2020-08-02      | 3                         | TAREA     | 456        | 123         |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | actividad | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | TAREA     | 123         | 456          | 2020-08-02             | 3               |
      | TAREA     | 345         | 456          | 2020-08-01             | 6               |
      | TAREA     | 345         | 456          | 2020-08-02             | 6               |
      | TAREA     | 123         | 123          | 2020-08-01             | 4               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' filtrando por un rango de fechas
      | fechaInicio     | fechaFin        |
      | 2020-08-01      | 2020-08-01      |
    Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | actividad | tareaId     | proyectoId |
      | 2020-08-01      | 6                         | TAREA     | 345         | 456        |
      | 2020-08-01      | 4                         | TAREA     | 123         | 123        |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' es asignado al proyecto '456'
    Y el empleado con legajo '5' es asignado al proyecto '123'
    Y el empleado con legajo '5' realizo la siguiente carga de horas
      | actividad | tareaId     | proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | TAREA     | 123         | 456          | 2020-08-02             | 3               |
      | TAREA     | 345         | 456          | 2020-08-01             | 6               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' con filtro de actividad 'TAREA'
      Entonces se me devuelve la siguiente informacion
      |  fecha          | cantidadDeHorasTrabajadas | actividad | tareaId     | proyectoId   |
      | 2020-08-02      | 3                         | TAREA     | 123         | 456          |
      | 2020-08-01      | 6                         | TAREA     | 345         | 456          |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' cargo horas con los siguientes datos
      | actividad      | fechaCargaDeHoras      | horasTrabajadas |
      | VACACIONES     | 2020-08-02             | 8               |
      | ENFERMEDAD     | 2020-08-01             | 8               |
      | DIA_DE_ESTUDIO | 2020-08-03             | 8               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' sin filtros
    Entonces se me devuelve la siguiente informacion acerca de las horas cargadas
      |  fecha          | cantidadDeHorasTrabajadas | actividad          |
      | 2020-08-02      | 8                         | VACACIONES         |
      | 2020-08-01      | 8                         | ENFERMEDAD         |
      | 2020-08-03      | 8                         | DIA_DE_ESTUDIO     |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y existe un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' cargo horas con los siguientes datos
      | actividad      | fechaCargaDeHoras      | horasTrabajadas |
      | VACACIONES     | 2020-08-02             | 8               |
      | ENFERMEDAD     | 2020-08-01             | 3               |
      | DIA_DE_ESTUDIO | 2020-08-01             | 5               |
      | DIA_DE_ESTUDIO | 2020-08-03             | 8               |
    Cuando consulto las horas trabajadas por el empleado con legajo '5' con filtro de actividad 'DIA_DE_ESTUDIO'
    Entonces se me devuelve la siguiente informacion acerca de las horas cargadas
      |  fecha          | cantidadDeHorasTrabajadas | actividad          |
      | 2020-08-01      | 5                         | DIA_DE_ESTUDIO     |
      | 2020-08-03      | 8                         | DIA_DE_ESTUDIO     |

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Y no existe el empleado con legajo '33'
    Cuando consulto las horas trabajadas por el empleado con legajo '5' sin filtros
    Entonces recibo un mensaje indicandome que no existe dicho empleado