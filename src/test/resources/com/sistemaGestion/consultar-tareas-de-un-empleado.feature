# language: es

Característica: consultar las tareas de un empleado

  Escenario: como lider de proyecto, quiero consultar las tareas de un empleado existente,
  para saber en que esta trabajando
    Dado que existe el empleado
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando consulto las tareas que el empleado con legajo '5' tiene asignadas
    Entonces obtengo una lista de tareas