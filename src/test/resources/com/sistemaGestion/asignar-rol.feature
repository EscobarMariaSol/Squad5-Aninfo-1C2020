# language: es

Característica: asignar el rol a un empleado

  Escenario: como Lider de Recursos Humanos, quiero asignar el rol de un empleado,
    para así poder consultarlo
    Dado que soy lider de recursos humanos
    Y hay 1 empleado registrado
    Cuando quiero asignar el tipo de contrato a un empleado
    Entonces el rol queda registrado en la información del perfil del empleado.

  Escenario: como Lider de Recursos Humanos, quiero asignar el rol de un empleado,
  para así poder consultarlo
    Dado que soy lider de recursos humanos
    Y no hay empleados
    Cuando quiero asignar el rol a un empleado
    Entonces obtengo un mensaje que me indica que no puedo dado que no existe el empleado.