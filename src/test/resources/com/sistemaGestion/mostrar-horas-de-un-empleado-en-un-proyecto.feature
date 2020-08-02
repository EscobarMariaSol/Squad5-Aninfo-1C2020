# language: es

Característica: mostrar horas trabajadas por un empleado en un proyecto

  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y tengo un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

    Y el empleado es asignado a ese proyecto cuyo id es '456'

    Y este empleado carga las horas trabajadas en las siguientes tareas
      | tareaId     | actividad |proyectoId   | fechaCargaDeHoras      | horasTrabajadas |
      | 123         | PROYECTO  |456          | 2020-08-01             | 3               |
      | 456         | PROYECTO  |456          | 2020-08-02             | 4               |
      | 789         | PROYECTO  |123          | 2020-08-02             | 2               |

    Cuando consulto las horas trabajadas por el empleado en el proyecto cuyo id es '456'

    Entonces obtengo la siguiente informacion
      | legajo     | cantidadDeHorasTrabajadas   |
      | 5          | 7                           |


  Escenario: como Lider de Recursos Humanos, quiero consultar las horas trabajadas por un empleado en un proyecto,
  para así poder liquidar su sueldo.

    Dado que soy lider de recursos humanos
    Y tengo un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando consulto las horas trabajadas por el empleado en el proyecto cuyo id es '123'
    Entonces obtengo un mensaje que me indica que el empleado no tiene horas trabajadas en el proyecto ya que este no estaba asignado a el.
