package com.sistemaGestion;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class AplicarFiltrosStepDefinitions {
    @Cuando("aplico un filtro a la busqueda de informacion")
    public void aplicoUnFiltroALaBusquedaDeInformacion() {
    }

    @Entonces("obtengo un registro de la información asociada a ese filtro.")
    public void obtengo_un_registro_de_la_información_asociada_a_ese_filtro() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Y("el filtro no corresponde a los filtros soportados")
    public void el_filtro_no_corresponde_a_los_filtros_soportados() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("obtengo un mensaje que me indica que no hay informacion para mostrar.")
    public void obtengo_un_mensaje_que_me_indica_que_no_hay_informacion_para_mostrar() {
        // Write code here that turns the phrase above into concrete actions
    }
}
