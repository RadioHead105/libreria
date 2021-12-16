
package com.curso.libreria.repositorio;

import com.curso.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{

    
@Query("SELECT c FROM Autor c WHERE c.nombre = :pepe")
public Autor buscarxNombre(@Param("pepe") String nombre);
    


    
    

        




}
