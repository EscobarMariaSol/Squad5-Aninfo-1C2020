# language: es

Característica: Cargar informacion basica de empleado

  Escenario: Como Líder de Recursos Humanos, quiero ingresar la información básica de un empleado, para que quede asentado en la empresa.
    Dado que soy un lider de recursos humanos
    Y quiero guardar un empledo con  los siguientes atributos
       | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           |
       | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR |
    Cuando guardo el nuevo empleado
    Entonces se guarda OK

