# language: es

Característica: dar de baja un empleado determinado

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que no esta asignado a ningun proyecto, ni tiene tareas ni tickets,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con los atributos:
        | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
        | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y no forma parte de ningun proyecto
    Y no tiene tickets asignados
    Cuando doy de baja al empleado con legajo '5'
    Entonces no se incluye al empleado con legajo '5' al consultar por los empleados

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y no tiene tareas ni tickets a su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con los atributos:
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y forma parte del proyecto '1'
    Y no tiene tickets asignados
    Cuando doy de baja al empleado con legajo '5'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '5' porque forma parte de un proyecto

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y tiene tareas a su cargo,
  y no tiene tickets a su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con los atributos:
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y forma parte del proyecto '1'
    Y no tiene tickets asignados
    Cuando doy de baja al empleado con legajo '5'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '5' porque forma parte de un proyecto

  Escenario: como Lider de Recursos Humanos,
  quiero dar de baja a un empleado existente segun su legajo,
  que esta asignado a un proyecto, y tiene tareas y ticketsa su cargo,
  para indicar que ya no forma parte de la empresa
    Dado que soy lider de recursos humanos
    Y existe el empleado con los atributos:
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Y forma parte del proyecto '1'
    Y tiene el ticket '1' asignado
    Cuando doy de baja al empleado con legajo '5'
    Entonces recibo un mensaje de que no puedo dar de baja al empleado '5' porque forma parte de un proyecto
    Y recibo un mensaje de que no puedo dar de baja al empleado '5' porque tiene un ticket asignado