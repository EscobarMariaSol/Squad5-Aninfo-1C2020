# language: es

Característica: cargar horas a una tarea

  Escenario: Como Desarrollador, quiero cargar las horas invertidas en una tarea, para informar el trabajo realizado.
    Dado que soy un desarrollador con legajo "1"
    Cuando cargo 3 horas trabajadas a una tarea cuyo id es "123" del proyecto con id "456"
    Entonces las horas son registradas correctamente

  Escenario: No se deber poder cargar horas fuera del mes vigente.
    Dado que soy un desarrollador con legajo "1"
    Cuando cargo 3 horas trabajadas en un mes no vigente a una tarea cuyo id es "123" del proyecto con id "456"
    Entonces se me indica que no puedo cargar horas

  Escenario: Como desarrollador quiero poder cargar horas por vacaciones
    Dado que soy un desarrollador con legajo "1"
    Cuando cargo 3 horas correspondientes a vacaciones
    Entonces las horas son registradas correctamente
