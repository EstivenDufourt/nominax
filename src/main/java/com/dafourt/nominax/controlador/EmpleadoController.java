package com.dafourt.nominax.controlador;

import com.dafourt.nominax.entidad.Empleado;
import com.dafourt.nominax.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public String mostrarEmpleados (Model model) {
        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @GetMapping("/empleados/registro")
    public String mostrarFormularioRegistroEmpleado(Model model) {
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        return "formulario-empleado";
    }

    
    @PostMapping("/empleados/registro")
    public String formularioRegistroEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }
    
    @GetMapping("/empleados/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        model.addAttribute("empleado", empleado);
        return "formulario-empleado";
    }


    @PostMapping("/empleados/editar/{id}")
    public String actualizarEmpleado(@PathVariable("id") Long id, @ModelAttribute("empleado") Empleado empleado, BindingResult result) {
        if (result.hasErrors()) {
            empleado.setId(id);
            return "formulario-empleado";
        }
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }


    @GetMapping("/empleados/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        empleadoRepository.delete(empleado);
        return "redirect:/empleados";
    }


}

