# language: es

Característica: Asignar seniority a un empleado

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
    para así conocer la seniority del mismo.
    Dado que soy Lider de Recursos Humanos
    Y existe un empleado con legajo '1'
    Cuando asigno la seniority 'Junior' a dicho empleado
    Entonces al empleado con legajo '1' se le asigna la seniority 'Junior'

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
  para así conocer la seniority del mismo.
    Dado que soy el lider de recursos humanos
    Y no existe el empleado con legajo '123'
    Cuando quiero asignar la seniority 'Junior' a dicho empleado
    Entonces obtengo un mensaje indicando que el empleado con legajo '123' no pudo ser encontrado
