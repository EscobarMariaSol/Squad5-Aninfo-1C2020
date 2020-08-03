# language: es

Característica: cargar horas a una tarea

  Escenario: Como Desarrollador, quiero cargar las horas invertidas en una tarea, para informar el trabajo realizado.
    Dado que soy un desarrollador con legajo "1"
    Cuando cargo 3 horas trabajadas en el dia "2020-08-02" a una tarea cuyo id es "123" del proyecto con id "456"
    Entonces las horas son registradas correctamente
