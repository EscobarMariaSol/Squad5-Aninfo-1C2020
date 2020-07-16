# language: es

Característica: Modificar el perfil de un empleado feterminado

  Escenario: Como Lider de recursos humanos, quiero modificar el nombre de un empleado,
  para mantenerlo actualizado.
    Dado que soy un lider de recursos humanos
    Y existe el  empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando cambio el nombre del empleado por 'Rowena'
    Entonces el empleado con legajo '5' presenta los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Rowena      | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

  Escenario: Como Lider de recursos humanos, quiero modificar el apellido de un empleado,
  para mantenerlo actualizado.
    Dado que soy un lider de recursos humanos
    Y existe el  empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando cambio el apellido del empleado por 'Weasley'
    Entonces el empleado con legajo '5' presenta los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Weasley    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

  Escenario: Como Lider de recursos humanos, quiero modificar el dni de un empleado,
  para mantenerlo actualizado.
    Dado que soy un lider de recursos humanos
    Y existe el  empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando cambio el dni del empleado por '15362738'
    Entonces el empleado con legajo '5' presenta los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | 15362738 |1979-09-19         | 5      |  full_time    | DESARROLLADOR | true   |

  Escenario: Como Lider de recursos humanos, quiero modificar la fecha de nacimiento de un empleado,
  para mantenerlo actualizado.
    Dado que soy un lider de recursos humanos
    Y existe el  empleado con los atributos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 | 1979-09-19        | 5      |  full_time    | DESARROLLADOR | true   |
    Cuando cambio la fecha de nacimiento del empleado por '1976-07-07'
    Entonces el empleado con legajo '5' presenta los siguientes datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 | 1976-07-07        | 5      |  full_time    | DESARROLLADOR | true   |

  Escenario: Como Lider de recursos humanos, quiero modificar los datos de un empleado y el empleado no existe.
    Dado que soy un lider de recursos humanos
    Y no existe el empleado con legajo '5'
    Cuando ingreso los nuevos datos
      | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
      | Hermione    | Granger    | W3508429 | 1979-09-19        | 5      |  full_time    | DESARROLLADOR | true   |
    Entonces se me informa que el empleado con legajo '5' no existe

