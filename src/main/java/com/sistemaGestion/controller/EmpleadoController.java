package sistemaGestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sistemaGestion.model.Empleado;
import sistemaGestion.service.EmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping(value = "/")
    public ResponseEntity consultarEmpleados() {
        return ResponseEntity.ok(empleadoService.consultarEmpleados());
    }

}
