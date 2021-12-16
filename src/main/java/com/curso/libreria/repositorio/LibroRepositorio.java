
package com.curso.libreria.repositorio;

import com.curso.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{

@Query("SELECT pepe from Libro pepe where pepe.titulo = :fuifu")
public Libro buscarxNombre(@Param("fuifu") String nombre);

}
