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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String cargarEditorial(ModelMap model, @RequestParam (required = false) String id) {
        
        if (id==null){
        model.addAttribute("editorial", new Editorial());
       
        }else {       
            try {
                model.addAttribute("editorial", es.findById(id));     //a este catch no va a entrar nunca
            } catch (ErrorServicios ex) {
                ex.printStackTrace();
            }
               
        }
        
        return "editorial-cargar";
    }
   
    // nombre = "CARLA" --> ESTO LLEGA ASI DE HTML, DEL INPUT, DEL ATRIBUTO name
    // HAY QUE ASIGNARSELO A UNA VARIABLE DE JAVA A TRAVES DE @requestParam
    @PostMapping("/guardar")
    public String guardarEditorial(RedirectAttributes redirectAttributes, ModelMap model, @ModelAttribute Editorial editorial){                                                                                                                                                                                                                                                                                                  
       
      try{  
       es.registrar(editorial);
       redirectAttributes.addFlashAttribute("sucess", "La editorial se cargó correctamente.");
     
      } catch (ErrorServicios error){
          redirectAttributes.addFlashAttribute("error", error.getMessage());
          
      }
       
 //-----> POR QUÉ ACA HABÍA QUE PONER UN REDIRECT Y NO UN TEMPLATE?? <--------
        return "redirect:/editorial/lista";
    }        
    



}