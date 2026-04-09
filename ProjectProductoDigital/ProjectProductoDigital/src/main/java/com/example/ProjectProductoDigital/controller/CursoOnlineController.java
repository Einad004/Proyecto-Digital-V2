package com.example.ProjectProductoDigital.controller;


import com.example.ProjectProductoDigital.model.CursoOnline;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.ProjectProductoDigital.service.CursoOnlineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoOnlineController {

    private final CursoOnlineService service;

    public CursoOnlineController(CursoOnlineService service) {
        this.service = service;
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return service.healthCheck();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody CursoOnline curso) {
        Object respuesta = service.crear(curso);

        if (respuesta instanceof String) {
            return ResponseEntity.badRequest().body(respuesta);
        }

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/")
    public ResponseEntity<List<CursoOnline>> listarTodo() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<CursoOnline>> filtrar(
            @RequestParam(required = false) Boolean certificado,
            @RequestParam(required = false) Double precioMax) {

        return ResponseEntity.ok(service.filtrar(certificado, precioMax));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id,
                                        @RequestBody CursoOnline datos) {

        CursoOnline actualizado = service.actualizar(id, datos);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {

        if (service.eliminar(id)) {
            return ResponseEntity.ok("Curso eliminado correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {

        CursoOnline curso = service.buscarPorId(id);

        if (curso == null) {
            return ResponseEntity.status(404).body("Curso no encontrado");
        }

        return ResponseEntity.ok(curso);
    }
}