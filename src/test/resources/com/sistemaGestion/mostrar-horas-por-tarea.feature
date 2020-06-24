# language: es

Característica: mostrar horas trabajadas por un empleado en una tarea

  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
    para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y hay 1 empleado registrado
    Cuando realizo una búsqueda de un empleado en el sistema
    Y selecciono sus tareas asignadas
    Entonces obtengo la información detallada de la cantidad de horas trabajadas por el empleado en cada tarea.


  Escenario: como lider de recursos humanos quiero consultar las horas trabajadas por un empleado en una tarea,
  para poder liquidar su sueldo.
    Dado que soy el lider de recursos humanos
    Y no hay empleados registrado
    Cuando realizo una búsqueda de un empleado en el sistema
    Entonces obtengo un mensaje indicandome que no hay empleados registrados