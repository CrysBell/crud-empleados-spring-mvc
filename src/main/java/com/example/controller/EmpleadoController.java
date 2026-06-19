package com.example.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private static final Logger LOG = Logger.getLogger("EmpleadoController");

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

    @GetMapping("/listar")
    public String listarEmpleados(Model model) {

        model.addAttribute("empleados", empleadoService.getAllEmpleados());

        // Mostrar la vista del archvo HTML
        return "listadoEmpleados";
    }

    // Metodo que muestra el formulario de creación de empleados
    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model, @ModelAttribute Empleado empleado) {
        // Se necesitan los departamentos desde la capa de servicios
        model.addAttribute("departamentos", departamentoService.getAllDepartamentos());

        // Se necesita enviar un objeto Empleado vacio, para que se vinculen sus
        // propiedades con cada control (element, input, select, etc) del atributo

        //El codigo siguiente se comenta porque el objeto se pasa como atributo al modelo a traves
        // de la anotación @ModelAtribute que se recibe como un parametro del metodo
           //model.addAttribute("empleado", new Empleado());

        return "formularioAltaModificacion";
    }

    // Metodo para recibir por post los datos procedentes del formulario de creación
    // de empleados
    @PostMapping("/persistir")
    public String procesarFormularioAltaModificacion(@ModelAttribute Empleado empleado,
            @RequestParam String numerosTelefono,
            @RequestParam String direccionesCorreo) {

        LOG.info("Objeto empleado recibido");
        LOG.info(empleado.toString());
        LOG.info("Numeros de telefono recibidos: " + numerosTelefono);
        LOG.info("Direcciones de correo recibidas: " + direccionesCorreo);


        //Hy que procesar los datos de los telefonos y correos. que vienen en un String
        //separados por comas, y convertirlos en listas de objetos Telefono y Correo,
        //para luego agregarlo al objeto Empleado antes de persistirlo en la BD
        
        //Set<Telefono> telefonos = new HashSet<Telefono>();

        if (!numerosTelefono.isEmpty() && !numerosTelefono.isBlank()) {

        String[] arrayNumerosTelefono = numerosTelefono.split(";");
        List<String> listadoNumeros = Arrays.asList(arrayNumerosTelefono);

            listadoNumeros.forEach(numero -> {
               empleado.getTelefonos().add(Telefono.builder().numero(numero).empleado(empleado).build());
            });
            
            //empleado.setTelefonos(telefonos);
        }

        if (!direccionesCorreo.isEmpty() && !direccionesCorreo.isBlank()){
        String[] arrayDirCorreos = direccionesCorreo.split(";");
        List<String> listadoCorreos = Arrays.asList(arrayDirCorreos);

            listadoCorreos.forEach(dirCorreo -> {
                empleado.getEmails().add(Correo.builder().email(dirCorreo).empleado(empleado).build());
            });
        }

        // Se recibe un objeto Empleado con los datos del formulario
        // Se envía a la capa de servicios para que lo guarde en la BD
        empleadoService.saveEmpleado(empleado);
        // empleadoService.saveEmpleado(empleado);
        return "redirect:/empleados/listar";
    }



}
