# language: es

Característica: consultar el perfil de un empleado

  Escenario: como lider de recursos humanos, quiero consultar el perfil del empleado con legajo 156, existente, para conocer sus datos
    Dado que soy un lider de recursos humanos
    Y existe el empleado con legajo '156'
    Cuando consulto los datos del empleado con legajo '156'
    Entonces obtengo los datos del empleado  con legajo '156'

  Escenario: como lider de recursos humanos, quiero consultar el perfil del empleado con legajo 156, no existente, para conocer sus datos
    Dado que soy un lider de recursos humanos
    Y no existe el empleado con legajo '156'
    Cuando consulto los datos del empleado con legajo '156'
    Entonces obtengo un mensaje indicando que el empleado con legajo '156' no pudo ser encontrado