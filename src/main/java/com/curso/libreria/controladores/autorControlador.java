package com.curso.libreria.controladores;

import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autor")
public class autorControlador {

    @Autowired
    AutorServicio as;

    @GetMapping("")
    public String autor() {

        return "autor";
    }

//   @PostMapping("/cargar")
//   public String cargar(Model model,RedirectAttributes redirectAtributes, @RequestParam String nombre){
//       try{
//            as.registrar(nombre);
//       }catch (Exception e){
//           
//       model.addAttribute("error", e.getMessage());
//       redirectAtributes.addFlashAttribute("error", e.getMessage());
//       }
//       return "autor-cargar";
//       }
//   
//   @GetMapping("/cargar")
//   public String cargar(){  
//      return "autor-cargar" ;
//   }
//           
//
//   @GetMapping("/autor-cargar")
//   public String autorcargar(){
//       
//      return "redirect:/autor-lista" ;
//   }
//   
//   @GetMapping("/modificar")
//   public String autormodificar(){
//       
//       return "autor-modificar";
//   }
//
//   @GetMapping("/lista")
//   public String listaAutor(Model model){
//      model.addAttribute("persona", as.mostrarAll());
//       return "autor-lista";
//   }
}
