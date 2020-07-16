# language: es

Característica: mostrar horas trabajadas por un empleado en una tarea

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
    para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Felipe      | Codeo      | W3508429 |1979-09-19         | 123    |  full-time    | DESARROLLADOR | true   |
    Y el empleado con legajo '123' cargo 6 horas en la tarea '1', del proyecto '1', el dia '2020-04-03'
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1', para la fecha '2020-04-03'
    Entonces se me indica que el empleado con legajo '123' trabajo 6 horas en la tarea '1', del proyecto '1', para la fecha '2020-04-03'

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Felipe      | Codeo      | W3508429 |1979-09-19         | 123    |  full-time    | DESARROLLADOR | true   |
    Y el empleado con legajo '123' no cargo horas en la tarea '1', del proyecto '1', el dia '2020-04-03'
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1', para la fecha '2020-04-03'
    Entonces se me indica que el empleado con legajo '123' trabajo 0 horas en la tarea '1', del proyecto '1', para la fecha '2020-04-03'

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y no existe el empleado con legajo '123' cargo 6 horas en la tarea '1', del proyecto '1', el dia '2020-04-03'
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1', para la fecha '2020-04-03'
    Entonces se me informa que no puedo realizar dicha accion ya que el emplado con legajo '123' no existe.