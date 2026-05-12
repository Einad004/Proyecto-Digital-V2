package com.example.ProjectProductoDigital.repositories;

import com.example.ProjectProductoDigital.model.PlataformaProveedora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlataformaRepository extends JpaRepository<PlataformaProveedora, Integer> {

    @Query("""
        SELECT p
        FROM PlataformaProveedora p
        ORDER BY p.id
    """)
    List<PlataformaProveedora> listarTodasOrdenadas();

    @Query("""
        SELECT p
        FROM PlataformaProveedora p
        WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
        ORDER BY p.id
    """)
    List<PlataformaProveedora> filtrarPorNombre(@Param("nombre") String nombre);

    @Query("""
        SELECT p
        FROM PlataformaProveedora p
        WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
          AND (:url IS NULL OR LOWER(p.url) LIKE LOWER(CONCAT('%', :url, '%')))
    """)
    List<PlataformaProveedora> buscarPorNombreYUrl(@Param("nombre") String nombre,
                                                   @Param("url") String url);
}
