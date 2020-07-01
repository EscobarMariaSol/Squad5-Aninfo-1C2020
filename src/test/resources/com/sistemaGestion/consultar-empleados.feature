# language: es

Característica: consultar empleados

  Escenario: como lider de recursos humanos quiero consultar los empleados de la empresa para saber quienes son
    Dado que soy un lider de recursos humanos
    Y hay empleados con los siguientes atributos
        | nombre      | apellido   | dni      | fechaDeNacimiento | legajo | contrato      | rol             | activo |
        | Hermione    | Granger    | W3508429 |1979-09-19         | 5      |  full-time    | DESARROLLADOR   | true   |
        | Harry       | Potter     | 8219737  |1984-04-09         | 548    |  part-time    | UX              | true   |
        | Felipe      | Codeo      | 1281728  |1980-07-23         | 234    |  full-time    | LIDER_PROYECTO  | true   |
    Cuando consulto los empleados
    Entonces obtengo un listado de los 3 empleados

  Escenario: como lider de recursos humanos quiero consultar los empleados de la empresa para saber quienes son
    Dado que soy un lider de recursos humanos
    Y no hay empleados
    Cuando consulto los empleados
    Entonces obtengo un listado vacio