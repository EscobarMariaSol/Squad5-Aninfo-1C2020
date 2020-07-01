# language: es

Característica: Asignar seniority a un empleado

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
    para así conocer la seniority del mismo.
    Dado que soy Lider de Recursos Humanos
    Y existe el empleado con los atributos
        | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol           | activo |
        | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR | true   |
    Cuando asigno la seniority 'Junior' al empleado con legajo '5'
    Entonces al empleado con legajo '5' se le asigna la seniority 'Junior'

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
  para así conocer la seniority del mismo.
    Dado que soy el lider de recursos humanos
    Y no existe el empleado con legajo '123'
    Cuando asigno la seniority 'Junior' al empleado con legajo '123'
    Entonces obtengo un mensaje indicando que el empleado con legajo '123' no fue encontrado
