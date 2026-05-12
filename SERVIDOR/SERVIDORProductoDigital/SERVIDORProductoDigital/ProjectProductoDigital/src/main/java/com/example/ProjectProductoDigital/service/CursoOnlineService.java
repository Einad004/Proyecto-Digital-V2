package com.example.ProjectProductoDigital.service;

import com.example.ProjectProductoDigital.model.CursoOnline;
import com.example.ProjectProductoDigital.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoOnlineService {

    @Autowired
    private CursoRepository cursoRepository;

    public String healthCheck() {
        return "Servicio Curso Online operativo en Oracle!";
    }

    public Object crear(CursoOnline nuevoCurso) {
        if (cursoRepository.existsById(nuevoCurso.getId())) {
            return "Ya existe un Curso con ese ID";
        }
        return cursoRepository.save(nuevoCurso);
    }

    public List<CursoOnline> listarTodo() {
        return cursoRepository.listarCursosConPlataforma();
    }

    public CursoOnline actualizar(int id, CursoOnline datos) {
        if (cursoRepository.existsById(id)) {
            datos.setId(id);
            return cursoRepository.save(datos);
        }
        return null;
    }

    public boolean eliminar(int id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CursoOnline buscarPorId(int id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<CursoOnline> filtrar(Boolean certificado, Double precioMax) {
        return cursoRepository.filtrarCursos(certificado, precioMax);
    }

    public CursoOnline buscarPorParametros(String nombre, Integer idPlataforma) {
        List<CursoOnline> resultados = cursoRepository.buscarPorNombreYPlataforma(nombre, idPlataforma);

        if (resultados.isEmpty()) {
            return null;
        }

        return resultados.get(0);
    }
}
