# language: es

Característica: mostrar horas trabajadas por un empleado en una tarea

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
    para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 123    |  full-time    | DESARROLLADOR | true   |
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1', para la fecha '2020-04-03'
    Entonces obtengo la información de la cantidad de horas trabajadas por el empleado en la tarea indicada durante la fecha indicada.

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y existe el empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 123    |  full-time    | DESARROLLADOR | true   |
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1'
    Entonces obtengo la información de la cantidad de horas trabajadas por el empleado en la tarea indicada.


  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y no hay empleados registrados
    Cuando consulto las horas trabajadas por el empleado con legajo '123',en la tarea '1' del proyecto '1'
    Entonces se me informa que no puedo realizar dicha acción ya que el emplado con legajo '123' no existe.