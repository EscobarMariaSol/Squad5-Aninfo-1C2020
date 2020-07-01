# language: es

Característica: asignar el rol a un empleado

  Escenario: como Lider de Recursos Humanos, quiero asignar el rol de un empleado,
    para así poder consultarlo
    Dado que soy lider de Recursos Humanos y quiero asignar el rol a un empleado
    Y existe un empleado cuyo numero de legajo es '123'
    Cuando asigno el rol de 'Desarrollador' al empleado con legajo '123'
    Entonces el rol 'Desarrollador' queda registrado en la informacion personal del empleado con legajo '123'.

  Escenario: como Lider de Recursos Humanos, quiero asignar el rol de un empleado,
    para así poder consultarlo
    Dado que soy lider de Recursos Humanos y quiero asignar el rol a un empleado
    Y no existe un empleado con legajo '124'
    Cuando selecciono asignar rol 'Desarrollador' al empleado con legajo '124'
    Entonces se me indica que no existe el empleado con legajo '124'.