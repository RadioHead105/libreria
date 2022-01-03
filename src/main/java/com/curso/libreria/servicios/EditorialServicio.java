
package com.curso.libreria.servicios;
import com.curso.libreria.entidades.Editorial;
import com.curso.libreria.excepciones.ErrorServicios;
import com.curso.libreria.repositorio.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
 @Autowired
 private EditorialRepositorio editorialRepositorio;

    public void validar(String nombre) throws ErrorServicios {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicios("El nombre de la editorial no puede ser nulo o estar vacio.");
        }
    }
    
    @Transactional
    public Editorial registrar(Editorial editorial) throws ErrorServicios {
        
        validar(editorial.getNombre());
        
      
        if(editorial.getId().isEmpty()){   //si el id esta vacío significa que 
                                                //voy a cargar una editorial nueva
            if(null == editorialRepositorio.buscarxNombre(editorial.getNombre())){
                editorial.setAlta(true);
                editorialRepositorio.save(editorial);
            
            }  else {                                                          
                  throw new ErrorServicios("La editorial ya se encuentra en la base de datos.");
            }
            
        }      
                                        //si el id no esta vacío, significa que vamos a editar una editorial
        if(!editorial.getId().isEmpty()){   
                        //evita que modifiquemos una editorial con un nombre ya cargado || salvo que sea la misma editorial a modificar     
            if(null == editorialRepositorio.buscarxNombre(editorial.getNombre()) || (editorialRepositorio.buscarxNombre(editorial.getNombre())).getNombre().equals((findById(editorial.getId())).getNombre())){
            
                editorialRepositorio.save(editorial);          
            } else {
                throw new ErrorServicios("La editorial ya se encuentra en la base de datos.");
            }
        }       
              return editorial;
    }
    
    
    public Editorial findById(String id) throws ErrorServicios{
        
       Optional<Editorial> optionalEditorial = editorialRepositorio.findById(id);
        
       if(optionalEditorial.isPresent()){
           return optionalEditorial.get();
           
       } else{
           throw new ErrorServicios("La editorial no se encuentra en la base de datos");
       }
               
    }
     
    
    public List<Editorial> listarAll(){
        return editorialRepositorio.findAll();
    }
 

//  @Transactional
// public void dardeBaja(String nombre) throws ErrorServicios{
//        
//       Editorial editorial =  editorialRepositorio.buscarxNombre(nombre);           
//      
//        if(editorial == null){
//         throw new ErrorServicios("No existe la editorial ingresada en la base de datos");   
//        
//        } else {
//            editorial.setAlta(false); 
//            editorialRepositorio.save(editorial);
//           
//        }
// }
 
}
