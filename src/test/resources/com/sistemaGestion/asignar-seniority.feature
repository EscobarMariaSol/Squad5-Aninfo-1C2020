# language: es

Característica: Asignar seniority a un empleado

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
    para así conocer la seniority del mismo.
    Dado que soy Lider de Recursos Humanos
    Y existe un empleado con id 1
    Cuando asigno la seniority 'Junior' a dicho empleado
    Y consulto el perfil del empleado con id 1
    Entonces obtengo la información del empleado actualizada.

  Escenario: Como lider de recursos humanos quiero asignar la seniority de un empleado,
  para así conocer la seniority del mismo.
    Dado que soy el lider de recursos humanos
    Y no existe el empleado con id 123
    Cuando quiero asignar la seniority 'Junior' a dicho empleado
    Entonces obtengo un mensaje indicando que el empleado con id 123 no pudo ser encontrado
