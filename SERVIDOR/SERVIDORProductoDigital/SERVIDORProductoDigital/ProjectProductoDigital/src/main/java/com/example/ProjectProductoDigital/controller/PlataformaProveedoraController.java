package com.example.ProjectProductoDigital.controller;

import com.example.ProjectProductoDigital.model.PlataformaProveedora;
import com.example.ProjectProductoDigital.service.PlataformaProveedoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/plataformas")
public class PlataformaProveedoraController {

    private final PlataformaProveedoraService service;

    public PlataformaProveedoraController(PlataformaProveedoraService service) {
        this.service = service;
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return service.healthCheck();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody PlataformaProveedora plataforma) {
        Object respuesta = service.crear(plataforma);
        // Si el service devuelve un String, es porque hubo un error de validación (ej: ID duplicado)
        if (respuesta instanceof String) {
            return ResponseEntity.badRequest().body(respuesta);
        }
        return ResponseEntity.status(201).body(respuesta); // 201 Created es mejor para POST
    }

    @GetMapping("/")
    public ResponseEntity<List<PlataformaProveedora>> listarTodo() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<PlataformaProveedora>> filtrarPorNombre(
            @RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(service.filtrarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id,
                                        @RequestBody PlataformaProveedora datos) {
        // Usamos el objeto de plataforma, no el de curso
        PlataformaProveedora actualizado = service.actualizar(id, datos);

        if (actualizado == null) {
            return ResponseEntity.status(404).body("La plataforma con ID " + id + " no existe.");
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (service.eliminar(id)) {
            return ResponseEntity.ok("Plataforma eliminada correctamente");
        }

        return ResponseEntity.status(404).body("No se pudo eliminar: Plataforma no encontrada");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        PlataformaProveedora p = service.buscarPorId(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorParametros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String url) {
        PlataformaProveedora plataforma = service.buscarPorParametros(nombre, url);

        if (plataforma == null) {
            return ResponseEntity.status(404).body("Plataforma no encontrada con los parametros enviados");
        }

        return ResponseEntity.ok(plataforma);
    }
}