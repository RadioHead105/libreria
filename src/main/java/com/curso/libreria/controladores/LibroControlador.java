
package com.curso.libreria.controladores;

import com.curso.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

@Autowired
LibroServicio ls;




}
