Feature: Proyecto
  Prueba de concepto para un proyecto

  Scenario: el lider de proyecto quiere agregar una tarea al proyecto
    Given que soy un lider de proyecto
    When agrego una tarea al proyecto
    Then puedo ver la tarea en el proyecto