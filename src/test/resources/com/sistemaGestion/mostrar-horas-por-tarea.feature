# language: es

Característica: mostrar horas trabajadas por un empleado en una tarea

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Julio       | Diaz       | 18508429 |1969-09-19         | 345    |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '345' cargo 8 horas en la tarea '2', del proyecto '1', el dia '2020-04-06'
    Y el empleado con legajo '345' cargo 3 horas en la tarea '2', del proyecto '1', el dia '2020-04-07'
    Y el empleado con legajo '345' cargo 5 horas en la tarea '2', del proyecto '1', el dia '2020-04-08'
    Cuando consulto las horas trabajadas por el empleado con legajo '345',en la tarea '2' del proyecto '1'
    Entonces se me devuelve un listado con las horas trabajadas por el empleado con legajo '345', en la tarea '2', del proyecto '1'.


  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y no existe el empleado con legajo '456'
    Cuando consulto las horas trabajadas por el empleado con legajo '456',en la tarea '1' del proyecto '1'
    Entonces se me informa que no puedo realizar dicha accion ya que el emplado con legajo '456' no existe.

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Silvia      | Diaz       | 18508429 |1989-09-19         | 567    |  full_time    | UX            | true   |
    Y el empleado con legajo '567' no cargo horas en la tarea '2', del proyecto '3'
    Cuando consulto las horas trabajadas por el empleado con legajo '567',en la tarea '2' del proyecto '3'
    Entonces se me devuelve un mensaje indicando que no hay horas cargadas por el empleado con legajo '567', en la tares 2, del proyecto '3'.

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Mabel       | Cruz       | 18508429 |1969-09-19         | 678    |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '678' cargo 8 horas en la tarea '2', del proyecto '2', el dia '2020-04-13'
    Cuando consulto las horas trabajadas por el empleado con legajo '678',en la tarea '2', del proyecto '2', el dia '2020-04-13'
    Entonces se me devuelve un listado con las horas trabajadas por el empleado con legajo '678', en la tarea '2', del proyecto '2', el dia '2020-04-13'.
