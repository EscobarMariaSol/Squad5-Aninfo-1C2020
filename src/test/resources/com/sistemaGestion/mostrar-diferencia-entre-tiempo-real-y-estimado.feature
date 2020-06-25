# language: es

Característica: mostrar diferencia entre tiempo real y tiempo estimado

  Escenario: como Lider de Recursos Humanos, quiero consultar la diferencia entre el tiempo real
    que un empleado incurrio en una tarea y el tiempo estimado para la misma,
    para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 empleado asignado a un proyecto
    Y finalizo una tarea asignada que cuenta con un tiempo estimado para su desarrollo
    Cuando consulto diferencia entre tiempo real y tiempo estimado de una tarea asignada a un empleado
    Entonces se muestra el valor correspondiente al desvio entre el tiempo real invertido y el estimado.

  Escenario: omo Lider de Recursos Humanos, quiero consultar la diferencia entre el tiempo real
  que un empleado incurrio en una tarea y el tiempo estimado para la misma,
  para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 empleado asignado a un proyecto
    Y no tiene una tarea asignada
    Cuando consulto diferencia entre tiempo real y tiempo estimado de una tarea asignada a un empleado
    Entonces se muestra un mensaje indicando que el empleado no tiene asignada ninguna tarea.

  Escenario: como Lider de Recursos Humanos, quiero consultar la diferencia entre el tiempo real
  que un empleado incurrio en una tarea y el tiempo estimado para la misma,
  para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay 1 empleado asignado a un proyecto
    Y tiene una tarea asignada que cuenta con un tiempo estimado para su desarrollo
    Y la tarea no ha sido finalizada
    Cuando consulto diferencia entre tiempo real y tiempo estimado de una tarea asignada a un empleado
    Entonces se muestra un mensaje indicando que no se puede calcular la diferencia de una tarea que no ha sido finalizada.

  Escenario: como Lider de Recursos Humanos, quiero consultar la diferencia entre el tiempo real
  y el tiempo estimado de un proyecto, para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y hay proyectos en curso
    Cuando consulto diferencia entre tiempo real y tiempo estimado de un proyecto
    Entonces se muestra el valor de la diferencia, en horas, entre el tiempo real invertido y el tiempo estimado, para el proyecto consultado.

  Escenario: como Lider de Recursos Humanos, quiero consultar la diferencia entre el tiempo real
  y el tiempo estimado de un proyecto, para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y no hay proyectos registrados
    Cuando consulto diferencia entre tiempo real y tiempo estimado de un proyecto
    Entonces se muestra un mensaje indicando que no existen proyectos registrados.
