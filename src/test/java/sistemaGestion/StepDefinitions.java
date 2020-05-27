package sistemaGestion;



import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

public class StepDefinitions {
    @Dado("^que soy un lider de proyecto$")
    public void soy_lider_de_proyecto() throws Throwable {
        Empleado empleado = new Empleado();
        empleado.es_lider_de_proyecto();
    }

    @Cuando("^agrego una tarea al proyecto$")
    public void agrego_una_tarea() throws Exception {
    }

    @Entonces("^puedo ver la tarea en el proyecto$")
    public void ver_tarea_en_proyecto() throws Exception {
    }
}
