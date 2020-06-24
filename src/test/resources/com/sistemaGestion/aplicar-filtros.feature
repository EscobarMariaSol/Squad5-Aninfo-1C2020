# language: es

Característica: aplicar filtros para generar reportes

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
    para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Cuando aplico un filtro a la busqueda de informacion
    Entonces obtengo un registro de la información asociada a ese filtro.

  Escenario: como Lider de Recursos Humanos, quiero aplicar filtros,
  para generar reportes de la información asociada a los filtros aplicados
    Dado que soy lider de recursos humanos
    Cuando aplico un filtro a la busqueda de informacion
    Y el filtro no corresponde a los filtros soportados
    Entonces obtengo un mensaje que me indica que no hay informacion para mostrar.