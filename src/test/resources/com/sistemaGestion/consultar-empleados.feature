# language: es

Característica: consultar empleados

  Escenario: como lider de recursos humanos quiero consultar los empleados de la empresa para saber quienes son
    Dado que soy un lider de recursos humanos
    Y hay 3 empleados
    Cuando consulto los empleados
    Entonces obtengo un listado de los 3 empleados

  Escenario: como lider de recursos humanos quiero consultar los empleados de la empresa para saber quienes son
    Dado que soy un lider de recursos humanos
    Y no hay empleados
    Cuando consulto los empleados
    Entonces obtengo un listado vacio