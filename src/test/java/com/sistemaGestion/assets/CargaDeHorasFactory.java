package com.sistemaGestion.assets;

import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.enums.Actividad;

import java.time.LocalDate;
import java.util.Map;

public class CargaDeHorasFactory {

    public static CargaDeHoras crearCargaDeHoras(Map<String, String> atributosCargaDeHoras) {

        Long id = Long.parseLong(atributosCargaDeHoras.get("id"));
        Long codigoTarea = Long.parseLong(atributosCargaDeHoras.get("codigoTarea"));
        Long codigoProyecto = Long.parseLong(atributosCargaDeHoras.get("codigoProyecto"));
        String legajo = atributosCargaDeHoras.get("legajo");
        LocalDate fecha = LocalDate.parse(atributosCargaDeHoras.get("fecha");
        Float cantidadHoras = Float.valueOf(atributosCargaDeHoras.get("cantidadHoras"));
        Actividad actividad = Actividad.valueOf(atributosCargaDeHoras.get("actividad"));

        return new CargaDeHoras(id, codigoTarea, codigoProyecto, legajo, fecha, cantidadHoras, actividad);
    }

}
