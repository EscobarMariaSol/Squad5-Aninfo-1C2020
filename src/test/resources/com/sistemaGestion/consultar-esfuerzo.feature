# language: es

Característica: consultar esfuerzo segun area

  Escenario: como Lider de Recursos Humanos, quiero consultar el esfuerzo de cada área,
    para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Cuando consulto el esfuerzo segun area
    Entonces obtengo un listado con las areas de la empresa indicando para cada una el esfuerzo real como horas estimadas versus reales.

  Escenario: como Lider de Recursos Humanos, quiero consultar el esfuerzo de cada área,
  para así generar el reporte correspondiente.

    Dado que soy lider de recursos humanos
    Y no hay proyectos registrados en ningun area
    Cuando consulto el esfuerzo segun area
    Entonces obtengo un listado con las areas de la empresa con el esfuerzo sin especificar.