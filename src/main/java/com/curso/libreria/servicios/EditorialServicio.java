
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
    public Editorial registrar(String nombre) throws ErrorServicios {
        
        validar(nombre);

        Editorial editorial = editorialRepositorio.buscarxNombre(nombre);

        if (editorial == null) {
            Editorial editorial1 = new Editorial();
            editorial1.setNombre(nombre);
            editorial1.setAlta(true);
            editorialRepositorio.save(editorial1);
            return editorial1;
        } else {
            throw new ErrorServicios("La editorial ya se encuentra en la base de datos");
        }
    }
    
    @Transactional
    public Editorial registrar(String nombre, String id) throws ErrorServicios {
        
        validar(nombre);

        Optional <Editorial> editorial = editorialRepositorio.findById(id);

        if (editorial.isPresent()) {
            
           Editorial editorial1 = editorial.get();
            editorial1.setNombre(nombre);
            editorialRepositorio.save(editorial1);
            return editorial1;
        } else {
            throw new ErrorServicios("La editorial no se encuentra en la base de datos");
        }
    }
    
    
    public List<Editorial> listarAll(){
        return editorialRepositorio.findAll();
    }
 
//    @Transactional
// public Editorial modificar(String nombre, String nombreNuevo) throws ErrorServicios{
//     validar(nombre);
//             
//        Editorial editorial =  editorialRepositorio.buscarxNombre(nombre);           
//        
//        if (editorial != null){
//         editorial.setNombre(nombreNuevo);
//        editorialRepositorio.save(editorial);
//        return editorial;
//        
//        } else {
//            
//        throw new ErrorServicios("No existe en la base de datos la editorial ingresada");
//        }                 
//     }
// 
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
