# language: es

Característica: Agregar un empleado a un proyecto

  Escenario: Como Lider de proyecto, quiero modificar la asignacion de un empleado en un proyecto,
  para informar que el empleado ya no puede realizar tareas dentro de ese proyecto.
    Dado que soy lider del proyecto '1' y quiero modificar la asignacion de un empleado en el proyecto
    Y existe un empleado con la siguiente informacion
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' fue asignado al proyecto '1' en la fecha '2020-07-19'
    Cuando modifico la asignación del empleado con legajo '5' en el proyecto '1', indicando que finalizo en la fecha '2020-08-02'
    Entonces la asignacion del empleado con legajo '5' en el proyecto '1' indica que su fecha de finalizacion es el '2020-08-02'.

  Escenario: Como Lider de proyecto, quiero modificar la asignacion de un empleado en un proyecto,
  para informar que el empleado ya no puede realizar tareas dentro de ese proyecto.
    Dado  que soy lider del proyecto '1' y quiero modificar la asignacion de un empleado en el proyecto
    Y existe un empleado con la siguiente informacion
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y el empleado con legajo '5' no ha sido asignado al proyecto '1'
    Cuando intento modificar la asignación del empleado con legajo '5' en el proyecto '1', indicando que finalizo en la fecha '2020-08-02'
    Entonces se me informa que no puedo realizar dicha acción porque el emplado no fue asignado a ese proyecto.

  Escenario: Como Lider de proyecto, quiero modificar la asignacion de un empleado en un proyecto,
  para informar que el empleado ya no puede realizar tareas dentro de ese proyecto.
    Dado  que soy lider del proyecto '1' y quiero modificar la asignacion de un empleado en el proyecto
    Y no existe el empleado con legajo '5'
    Cuando intento modificar la asignación del empleado con legajo '5' en el proyecto '1', indicando que finalizo en la fecha '2020-08-02'
    Entonces se me informa que no puedo realizar dicha acción porque el empladono existe.