# language: es

Característica: Agregar un empleado a un proyecto

  Escenario: Como Lider de proyecto, quiero agregar un empleado a un proyecto,
    para que el empleado pueda realizar tareas dentro de ese proyecto.
    Dado que soy lider del proyecto '1' y quiero agregar un empleado a mi proyecto
    Y hay un empleado cuyo legajo es '123'
    Cuando agrego al empleado, con lejago '123', al proyecto '1'
    Entonces el empleado '123' queda asignado al proyecto '1'.

  Escenario: Como Lider de proyecto, quiero agregar un empleado a un proyecto,
    para que el empleado pueda realizar tareas dentro de ese proyecto.
    Dado que soy lider del proyecto '2' y quiero agregar un empleado a mi proyecto
    Y no hay un empleado cuyo legajo es '1234'
    Cuando agrego al empleado, con lejago '1234', al proyecto '2'
    Entonces se me informa que no puedo agregar al empleado con legajo '1234', porque no existe.

