
package com.curso.libreria.servicios;

import com.curso.libreria.entidades.Autor;
import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.repositorio.AutorRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

 @Autowired
 private AutorRepositorio autorRepositorio;
 
 public void validar(String nombre) throws ErrorServicios{
     if(nombre == null || nombre.isEmpty()){
      throw new ErrorServicios("El nombre del autor no puede ser nulo."); 
    }  
    }

 @Transactional
 public Autor registrar( String nombre ) throws ErrorServicios{
     validar(nombre);
          
    Autor autor =  autorRepositorio.buscarxNombre(nombre);   
          
      if (autor == null){
        Autor autor1 = new Autor();
        autor1.setNombre(nombre);
        autorRepositorio.save(autor1);    
         return autor1;  
      }  else {
         return autor; 
      }
  }
 
@Transactional
 public Autor modificar(String nombre, String nombreNuevo) throws ErrorServicios{
     validar(nombre);
             
        Autor autor =  autorRepositorio.buscarxNombre(nombre);           
        if ((autor != null)){
        
         autor.setNombre(nombreNuevo);
        autorRepositorio.save(autor);
        return autor;
        
        } else {
            
         throw new ErrorServicios("No existe en la base de datos el autor ingresado");
        }                 
 }
  
 @Transactional
 public void dardeBaja(String nombre) throws ErrorServicios{
        
        Autor autor =  autorRepositorio.buscarxNombre(nombre);           
      
        if(autor == null){
         throw new ErrorServicios("No existe el autor ingresado en la base de datos");   
        
        } else {
            autor.setAlta(false); 
            autorRepositorio.save(autor);
           
        }
 }
 
 public List<Autor> mostrarAll(){
     
 return autorRepositorio.findAll();
     
 }
        
 
 
}
