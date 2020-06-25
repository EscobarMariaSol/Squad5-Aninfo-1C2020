# language: es

Característica: asignar el contrato a un empleado

  Escenario: como Lider de Recursos Humanos, quiero asignar el tipo de contrato de un empleado,
    para así poder consultarlo
    Dado que soy lider de recursos humanos
    Y hay 1 empleado registrado
    Cuando realizo una búsqueda del empleado
    Y le asigno un tipo de contrato
    Entonces el tipo de contrato queda registrado en la información del perfil del empleado.

  Escenario: como Lider de Recursos Humanos, quiero asignar el tipo de contrato de un empleado,
  para así poder consultarlo
    Dado que soy lider de recursos humanos
    Y no hay empleados
    Cuando quiero asignar el tipo de contrato a un empleado
    Entonces obtengo un mensaje que me indica que no puedo dado que no existe el empleado.