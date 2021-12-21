package com.curso.libreria.controladores;

import com.curso.libreria.entidades.Editorial;
import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.servicios.EditorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    EditorialServicio es;    
    
    @GetMapping("/lista")
    public String listaEditorial(ModelMap model){
        model.addAttribute("editoriales", es.listarAll());
        return "editorial-lista";
    }
    
    @GetMapping("/cargar")
    public String cargarEditorial(ModelMap model){
        
        model.addAttribute("editorial", new Editorial());
        
        
        return "editorial-cargar";
    }
   
    // nombre = "CARLA" --> ESTO LLEGA ASI DE HTML, DEL INPUT, DEL ATRIBUTO name
    // HAY QUE ASIGNARSELO A UNA VARIABLE DE JAVA A TRAVES DE @requestParam
    @PostMapping("/guardar")
    public String guardarEditorial(ModelMap model, @RequestParam(name = "nombre")String nombrePalito, @RequestParam String id){                                                                                                                                                                                                                                                                                                  
       
        if(id!=null){
            try {
                model.addAttribute("editorial", es.registrar(nombrePalito, id)) ;
                model.addAttribute("correcto", "La editorial se editó correctamente");
            } catch (ErrorServicios ex) {
               ex.getMessage();
            }
        } else {
            try {
            model.addAttribute("editorial", es.registrar(nombrePalito));
           
            model.addAttribute("correcto", "La editorial se cargó correctamente.");
        } catch (ErrorServicios pepe) {
            model.addAttribute("error", pepe.getMessage());
        }        
        
        }
        
        return "editorial-cargar";
    }        
    



}