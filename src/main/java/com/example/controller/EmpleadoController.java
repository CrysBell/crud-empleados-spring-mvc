package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Empleado;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;





@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    
    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

    
    @GetMapping("/listar")
    public String listarEmpleados(Model model){

        model.addAttribute("empleados", empleadoService.getAllEmpleados());

        //Mostrar la vista del archvo HTML
        return "listadoEmpleados";
    }

    //Metodo que muestra el formulario de creación de empleados
    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model){
    //Se necesitan los departamentos desde la capa de servicios
        model.addAttribute("departamentos", departamentoService.getAllDepartamentos());

    //Se necesita enviar un objeto Empleado vacio, para que se vinculen sus propiedades
    //con cada control (element, input, select, etc) del atributo
        model.addAttribute("empleados", new Empleado());

        return "formularioAltaModificacion";
    }




}
