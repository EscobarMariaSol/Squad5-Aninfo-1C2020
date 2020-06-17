package sistemaGestion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoController {

    @RequestMapping(value = "/empleados", method = RequestMethod.GET)
    public String consultarEmpleados() {
        return "custom";
    }

}
