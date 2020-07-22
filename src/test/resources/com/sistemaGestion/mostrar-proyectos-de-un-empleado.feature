# language: es

Característica: mostrar proyectos en los que trabaja un proyecto
  Escenario: como Lider de Recursos Humanos, quiero ver los proyectos que tiene asignado un empleado

    Dado que soy lider de recursos humanos

    Y tengo un empleado con los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

    Y el empleado es asignado a ese proyecto cuyo id es '123'

    Y el empleado es asignado a ese proyecto cuyo id es '456'

    Cuando consulto los proyectos de este empleado cuyo id es '5'

    Entonces obtengo un listado proyectos asignados a este empleado



  Escenario: Como Lider de recursos humanos, quiero que se me informe si quiero consultar sobre un empleado que no existe

    Dado que soy lider de recursos humanos

    Cuando consulto los proyectos de este empleado cuyo id es '2'

    Entonces obtengo un mensaje de que el empleado no existe