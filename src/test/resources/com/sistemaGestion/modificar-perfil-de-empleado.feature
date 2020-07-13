# language: es

Característica: Modificar el perfil de un empleado feterminado

  Escenario: Como Lider de recursos humanos, quiero modificar los datos del perfil de un empleado,
  para mantenerlos actualizados.
    Dado que soy un lider de recursos humanos
    Y existe el  empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR | true   |
    Cuando cambio el nombre del empleado por 'Rowena'
    Entonces el empleado con legajo '5' presenta los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Rowena    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR | true   |


