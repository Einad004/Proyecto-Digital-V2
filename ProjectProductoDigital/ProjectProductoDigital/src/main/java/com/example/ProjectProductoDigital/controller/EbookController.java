package com.example.ProjectProductoDigital.controller;

import com.example.ProjectProductoDigital.model.Ebook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ebook")
public class EbookController {

    private static List<Ebook> ebooks = new ArrayList<>();

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Servicio Ebook Operativo (ID: int)!";
    }

    // CREATE: Crear un nuevo Ebook
    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Ebook nuevoEbook) {

        // 🔍 Validar si ya existe el ID
        for (Ebook eb : ebooks) {
            if (eb.getId() == nuevoEbook.getId()) {
                return ResponseEntity
                        .badRequest()
                        .body("Ya existe un Ebook con ese ID");
            }
        }

        ebooks.add(nuevoEbook);
        return ResponseEntity.ok(nuevoEbook);
    }

    // READ: Obtener la lista de todos los ebooks
    @GetMapping("/")
    public ResponseEntity<List<Ebook>> listarTodo() {
        return ResponseEntity.ok(ebooks);
    }

    //FILTRAR (por formato)
    @GetMapping("/filtrar/formato/{formato}")
    public ResponseEntity<List<Ebook>> filtrarPorFormato(@PathVariable String formato) {
        List<Ebook> filtrados = new ArrayList<>();

        for (Ebook eb : ebooks) {
            if (eb.getFormato().equalsIgnoreCase(formato)) {
                filtrados.add(eb);
            }
        }

        return ResponseEntity.ok(filtrados);
    }

    // UPDATE: Actualizar por ID (ahora recibe int)
    @PutMapping("/{id}")
    public ResponseEntity<Ebook> actualizar(@PathVariable int id, @RequestBody Ebook datosActualizados) {
        for (Ebook eb : ebooks) {
            if (eb.getId() == id) {
                eb.setNombre(datosActualizados.getNombre());
                eb.setPrecioBase(datosActualizados.getPrecioBase());
                eb.setTamanoMB(datosActualizados.getTamanoMB());
                eb.setNumeroPaginas(datosActualizados.getNumeroPaginas());
                eb.setFormato(datosActualizados.getFormato());
                return ResponseEntity.ok(eb);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Eliminar por ID (ahora recibe int)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        // Usamos removeIf con la comparación de enteros
        boolean eliminado = ebooks.removeIf(eb -> eb.getId() == id);

        if (eliminado) {
            return ResponseEntity.ok("Ebook con ID " + id + " ha sido eliminado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {

        for (Ebook eb : ebooks) {
            if (eb.getId() == id) {
                return ResponseEntity.ok(eb);
            }
        }

        return ResponseEntity
                .status(404)
                .body("Ebook no encontrado");
    }
}