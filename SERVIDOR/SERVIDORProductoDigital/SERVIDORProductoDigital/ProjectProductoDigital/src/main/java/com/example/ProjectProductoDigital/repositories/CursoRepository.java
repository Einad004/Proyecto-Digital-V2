package com.example.ProjectProductoDigital.repositories;

import com.example.ProjectProductoDigital.model.CursoOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<CursoOnline, Integer> {

    @Query("""
        SELECT c
        FROM CursoOnline c
        JOIN FETCH c.plataformaProveedora p
        ORDER BY c.id
    """)
    List<CursoOnline> listarCursosConPlataforma();

    @Query("""
        SELECT c
        FROM CursoOnline c
        JOIN FETCH c.plataformaProveedora p
        WHERE (:certificado IS NULL OR c.certificado = :certificado)
          AND (:precioMax IS NULL OR c.precioBase <= :precioMax)
        ORDER BY c.id
    """)
    List<CursoOnline> filtrarCursos(@Param("certificado") Boolean certificado,
                                    @Param("precioMax") Double precioMax);

    @Query("""
        SELECT c
        FROM CursoOnline c
        JOIN FETCH c.plataformaProveedora p
        WHERE (:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
          AND (:idPlataforma IS NULL OR c.plataformaProveedora.id = :idPlataforma)
    """)
    List<CursoOnline> buscarPorNombreYPlataforma(@Param("nombre") String nombre,
                                                 @Param("idPlataforma") Integer idPlataforma);
}
