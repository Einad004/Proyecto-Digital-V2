package com.example.ProjectProductoDigital.controller;


import com.example.ProjectProductoDigital.model.CursoOnline;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoOnlineController {

    private static List<CursoOnline> cursos = new ArrayList<>();

    // Verificar servicio
    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Servicio Curso Online operativo!";
    }

    // CREATE
    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody CursoOnline nuevoCurso) {

        // 🔍 Validar si ya existe el ID
        for (CursoOnline eb : cursos) {
            if (eb.getId() == nuevoCurso.getId()) {
                return ResponseEntity
                        .badRequest()
                        .body("Ya existe un Curso con ese ID");
            }
        }

        cursos.add(nuevoCurso);
        return ResponseEntity.ok(nuevoCurso);
    }

    // LISTAR TODO
    @GetMapping("/")
    public ResponseEntity<List<CursoOnline>> listarTodo() {
        return ResponseEntity.ok(cursos);
    }

    // FILTRAR (por certificado)
    @GetMapping("/filtrar/certificado/{certificado}")
    public ResponseEntity<List<CursoOnline>> filtrarPorCertificado(@PathVariable boolean certificado) {

        List<CursoOnline> filtrados = new ArrayList<>();

        for (CursoOnline c : cursos) {
            if (c.isCertificado() == certificado) {
                filtrados.add(c);
            }
        }

        return ResponseEntity.ok(filtrados);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CursoOnline> actualizar(@PathVariable int id,
                                                  @RequestBody CursoOnline datosActualizados) {

        for (CursoOnline c : cursos) {
            if (c.getId() == id) {

                c.setNombre(datosActualizados.getNombre());
                c.setPrecioBase(datosActualizados.getPrecioBase());
                c.setTamanoMB(datosActualizados.getTamanoMB());
                c.setDuracionHoras(datosActualizados.getDuracionHoras());
                c.setCertificado(datosActualizados.isCertificado());

                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {

        boolean eliminado = cursos.removeIf(c -> c.getId() == id);

        if (eliminado) {
            return ResponseEntity.ok("Curso eliminado correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {

        for (CursoOnline c : cursos) {
            if (c.getId() == id) {
                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity
                .status(404)
                .body("Curso no encontrado");
    }
}