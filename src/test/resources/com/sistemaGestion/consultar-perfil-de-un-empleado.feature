# language: es

Característica: consultar el perfil de un empleado

  Escenario: como lider de recursos humanos quiero consultar el perfil del empleado existente 1 para conocer sus datos
    Dado que soy un lider de recursos humanos
    Y existe el empleado 1
    Cuando consulto los datos del empleado 1
    Entonces obtengo los datos del empleado 1

  Escenario: como lider de recursos humanos quiero consultar el perfil del empleado no existente 1 para conocer sus datos
    Dado que soy un lider de recursos humanos
    Y no existe el empleado 1
    Cuando consulto los datos del empleado 1
    Entonces obtengo un mensaje indicando que el empleado 1 no pudo ser encontrado