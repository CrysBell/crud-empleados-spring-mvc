package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    
    private final EmpleadoService empleadoService;

    
    //@GetMapping("/listar")
    //public String getMethodName(@RequestParam String param) {
    //    return new String();
    //}
    
    @GetMapping("/listar")
    public String listarEmpleados(Model model){

        model.addAttribute("empleados", empleadoService.getAllEmpleados());

        //Mostrar la vista del archvo HTML
        return "listadoEmpleados";
    }
}
