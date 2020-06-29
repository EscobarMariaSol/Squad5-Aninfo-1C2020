# language: es

Característica: dar de baja un empleado determinado

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que no esta asignado a ningun proyecto, ni tiene tareas ni tickets,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con legajo '156'
    Y no tiene tareas asignadas
    Y no tiene tickets asignados
    Y no forma parte de ningun proyecto
    Cuando doy de baja al empleado con legajo '156'
    Entonces no se incluye al empleado con legajo '156' al consultar por los empleados

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y no tiene tareas ni tickets a su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con legajo '156'
    Y no tiene tareas asignadas
    Y no tiene tickets asignados
    Y forma parte del proyecto 1
    Cuando doy de baja al empleado con legajo '156'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '156' porque forma parte de un proyecto

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y tiene tareas a su cargo,
  y no tiene tickets a su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con legajo '156'
    Y tiene la tarea 1 asignada
    Y no tiene tickets asignados
    Y forma parte del proyecto 1
    Cuando doy de baja al empleado con legajo '156'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '156' porque forma parte de un proyecto
    Y recibo un mensaje de que no puedo dar de baja al empleado '156' porque tiene una tarea asignado

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y tiene tareas y ticketsa su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con legajo '156'
    Y tiene la tarea 1 asignada
    Y tiene el ticket 1 asignado
    Y forma parte del proyecto 1
    Cuando doy de baja al empleado con legajo '156'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '156' porque forma parte de un proyecto
    Y recibo un mensaje de que no puedo dar de baja al empleado '156' porque tiene una tarea asignado
    Y recibo un mensaje de que no puedo dar de baja al empleado '156' porque tiene un ticket asignado