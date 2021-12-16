package com.curso.libreria.controladores;

import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.servicios.AutorServicio;
import com.curso.libreria.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainControlador {
    
    @GetMapping("/")
    public String index() {        
        return "index";                        
    }
    
    
    
    

}
