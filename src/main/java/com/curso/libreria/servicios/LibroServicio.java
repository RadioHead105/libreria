
package com.curso.libreria.servicios;


import com.curso.libreria.entidades.Autor;
import com.curso.libreria.entidades.Editorial;
import com.curso.libreria.entidades.Libro;
import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.repositorio.AutorRepositorio;
import com.curso.libreria.repositorio.EditorialRepositorio;
import com.curso.libreria.repositorio.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

 @Autowired
 private LibroRepositorio libroRepositorio;
 
 @Autowired
 private EditorialServicio editorialServ;
 
 @Autowired
 private AutorServicio autorServ;
 
 public void agregarLibro(long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresP, String nombreAutor, String nombreEditorial )throws ErrorServicios{
     
     validar(isbn, titulo, anio, ejemplares, ejemplaresP);
    
     Libro libro = libroRepositorio.buscarxNombre(titulo);
     
     if (libro == null){
     Libro libro1 = new Libro();
     libro.setIsbn(isbn);
     libro.setTitulo(titulo);
     libro.setAnio(anio);
     libro.setEjemplares(ejemplares);
     libro.setEjemplaresPrestados(ejemplaresP);
     libro.setEjemplaresRestantes(libro.getEjemplares()-libro.getEjemplaresPrestados());
     
     libro.setAutor(autorServ.registrar(nombreAutor));
     libro.setEditorial(editorialServ.registrar(nombreEditorial));

     libroRepositorio.save(libro);       
     }
 }
 
 @Transactional
 public void modificar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresP, String nombreAutor, String nombreEditorial )throws ErrorServicios{
     validar(isbn, titulo, anio, ejemplares, ejemplaresP);
     
     Libro libro = libroRepositorio.buscarxNombre(titulo);
       
     if (libro == null){
         throw new ErrorServicios("El libro solicitado no existe");
     } else {   
         libro.setIsbn(isbn);
         libro.setTitulo(titulo);
         libro.setAnio(anio);
         libro.setEjemplares(ejemplares);
         libro.setEjemplaresPrestados(ejemplaresP);
         libro.setEjemplaresRestantes(libro.getEjemplares()-libro.getEjemplaresPrestados());
       
        libroRepositorio.save(libro);
     }    
 }
 
    public void validar( Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresp)throws ErrorServicios{
        if (isbn == null){
            throw new ErrorServicios("El isbn no puede estar vacío");    
        }
        
        if(isbn < 0){
            throw new ErrorServicios("El isbn no puede ser un número negativo");
        }
        
        if(titulo.isEmpty() || titulo == null){
            throw new ErrorServicios("El título no puede estar vacío");       
        }
        
        if (anio == null ){
         throw new ErrorServicios("El año no puede estar vacío");
         }
        
        if (anio == 0 || anio < 0){
         throw new ErrorServicios("El año no puede ser igual o menor que 0");
         }
        
        if(ejemplares == null || ejemplares == 0 || ejemplares < 0){
            throw new ErrorServicios("El número de ejemplares no puede ser igual o menor que 0");
        }
     
        if(ejemplaresp == null || ejemplaresp < 0){
            throw new ErrorServicios("El número de ejemplares prestados no puede estar vacío o ser menor que 0");
        }
    }

public void eliminar(String titulo)throws ErrorServicios{
    
    Libro libro = libroRepositorio.buscarxNombre(titulo);
    if ((libro != null)){
        
        libro.setAlta(false);
        libroRepositorio.save(libro);
        
    }else{
        throw new ErrorServicios("No existe un libro con el título ingresado.");
    }        
}

}
