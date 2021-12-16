
package com.curso.libreria.repositorio;

import com.curso.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{

@Query("SELECT a from Editorial a WHERE a.nombre = :pepe")    
public Editorial buscarxNombre(@Param("pepe") String nombre);
    

}
