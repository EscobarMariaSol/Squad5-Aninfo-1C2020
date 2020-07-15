package com.sistemaGestion;

import com.sistemaGestion.assets.EmpleadoFactory;
import com.sistemaGestion.controller.EmpleadoController;
import com.sistemaGestion.dtos.PerfilEmpleadoDTO;
import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.repository.EmpleadoRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ModificarPerfilDeEmpleadoStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoController empleadoController;

    private PerfilEmpleadoDTO perfilEmpleadoDTO;
    private Empleado empleado;
    private ResponseEntity response;

    @Y("existe el  empleado con los atributos")
    public void existeElEmpleadoConLosAtributos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));
        empleado = EmpleadoFactory.crearEmpleado(empleados.get(0));
        empleadoController.ingresarEmpleado(empleado);
    }

    @Cuando("cambio el nombre del empleado por {string}")
    public void cambioElNombreDelEmpleadoPor(String nuevoNombre) {
        perfilEmpleadoDTO = new PerfilEmpleadoDTO.Builder().con(empleadoData -> {
            empleadoData.conNombre(nuevoNombre);
            empleadoData.conApellido(perfilEmpleadoDTO.getApellido());
            empleadoData.conDni(perfilEmpleadoDTO.getDni());
            empleadoData.conFechaNacimiento(perfilEmpleadoDTO.getFechaNacimiento());
            empleadoData.conLegajo(perfilEmpleadoDTO.getLegajo());
            empleadoData.conRol(perfilEmpleadoDTO.getRol());
            empleadoData.conContrato(perfilEmpleadoDTO.getContrato());
            empleadoData.conSeniority(perfilEmpleadoDTO.getSeniority());
            empleadoData.conActivo(perfilEmpleadoDTO.getActivo());
        }).build();
        response = empleadoController.actualizarEmpleado(perfilEmpleadoDTO);
    }

    @Cuando("cambio el apellido del empleado por {string}")
    public void cambioElApellidoDelEmpleadoPorWeasley(String nuevoApellido) {
        perfilEmpleadoDTO = new PerfilEmpleadoDTO.Builder().con(empleadoData -> {
            empleadoData.conNombre(perfilEmpleadoDTO.getNombre());
            empleadoData.conApellido(nuevoApellido);
            empleadoData.conDni(perfilEmpleadoDTO.getDni());
            empleadoData.conFechaNacimiento(perfilEmpleadoDTO.getFechaNacimiento());
            empleadoData.conLegajo(perfilEmpleadoDTO.getLegajo());
            empleadoData.conRol(perfilEmpleadoDTO.getRol());
            empleadoData.conContrato(perfilEmpleadoDTO.getContrato());
            empleadoData.conSeniority(perfilEmpleadoDTO.getSeniority());
            empleadoData.conActivo(perfilEmpleadoDTO.getActivo());
        }).build();
        response = empleadoController.actualizarEmpleado(perfilEmpleadoDTO);
    }

    @Cuando("cambio el dni del empleado por {string}")
    public void cambioElDniDelEmpleadoPor(String nuevoDni) {
        perfilEmpleadoDTO = new PerfilEmpleadoDTO.Builder().con(empleadoData -> {
            empleadoData.conNombre(perfilEmpleadoDTO.getNombre());
            empleadoData.conApellido(perfilEmpleadoDTO.getApellido());
            empleadoData.conDni(nuevoDni);
            empleadoData.conFechaNacimiento(perfilEmpleadoDTO.getFechaNacimiento());
            empleadoData.conLegajo(perfilEmpleadoDTO.getLegajo());
            empleadoData.conRol(perfilEmpleadoDTO.getRol());
            empleadoData.conContrato(perfilEmpleadoDTO.getContrato());
            empleadoData.conSeniority(perfilEmpleadoDTO.getSeniority());
            empleadoData.conActivo(perfilEmpleadoDTO.getActivo());
        }).build();
        response = empleadoController.actualizarEmpleado(perfilEmpleadoDTO);
    }

    @Cuando("cambio la fecha de nacimiento del empleado por {string}")
    public void cambioLaFechaDeNacimientoDelEmpleadoPor(String nuevaFechanacimiento) {
        perfilEmpleadoDTO = new PerfilEmpleadoDTO.Builder().con(empleadoData -> {
            empleadoData.conNombre(perfilEmpleadoDTO.getNombre());
            empleadoData.conApellido(perfilEmpleadoDTO.getApellido());
            empleadoData.conDni(perfilEmpleadoDTO.getDni());
            LocalDate fechaDeNacimiento = LocalDate.parse(nuevaFechanacimiento);
            empleadoData.conFechaNacimiento(fechaDeNacimiento);
            empleadoData.conLegajo(perfilEmpleadoDTO.getLegajo());
            empleadoData.conRol(perfilEmpleadoDTO.getRol());
            empleadoData.conContrato(perfilEmpleadoDTO.getContrato());
            empleadoData.conSeniority(perfilEmpleadoDTO.getSeniority());
            empleadoData.conActivo(perfilEmpleadoDTO.getActivo());
        }).build();
        response = empleadoController.actualizarEmpleado(perfilEmpleadoDTO);
    }

    @Cuando("ingreso los nuevos datos")
    public void ingresoLosNuevosDatos(DataTable empleadoDt) {
        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        perfilEmpleadoDTO = EmpleadoFactory.crearPerfilEmpleadoDTO(empleados.get(0));

        response = empleadoController.actualizarEmpleado(perfilEmpleadoDTO);
    }

    @Entonces("el empleado con legajo {string} presenta los siguientes datos")
    public void elEmpleadoConLegajoPresentaLosSiguientesDatos(String legajo, DataTable empleadoDt) {
        Empleado empleadoActualizado = empleadoRepository.findByLegajo(legajo).orElse(null);

        List<Map<String, String>> empleados = empleadoDt.asMaps(String.class, String.class);
        Empleado empleadoEsperado = EmpleadoFactory.crearEmpleado(empleados.get(0));

        Assert.assertEquals(empleadoEsperado, empleadoActualizado);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Entonces("se me informa que el empleado con legajo {string} no existe")
    public void seMeInformaQueElEmpleadoConLegajoNoExiste(String legajo) {
        Assert.assertEquals(
                "El empleado con legajo " + legajo + " no existe.",
                response.getBody()
        );
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
