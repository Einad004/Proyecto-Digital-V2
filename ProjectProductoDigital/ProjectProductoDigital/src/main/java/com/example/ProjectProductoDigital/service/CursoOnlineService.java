package com.example.ProjectProductoDigital.service;

import com.example.ProjectProductoDigital.model.CursoOnline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoOnlineService {

    private List<CursoOnline> cursos = new ArrayList<>();

    public String healthCheck() {
        return "Servicio Curso Online operativo!";
    }

    public Object crear(CursoOnline nuevoCurso) {
        for (CursoOnline c : cursos) {
            if (c.getId() == nuevoCurso.getId()) {
                return "Ya existe un Curso con ese ID";
            }
        }
        cursos.add(nuevoCurso);
        return nuevoCurso;
    }

    public List<CursoOnline> listarTodo() {
        return cursos;
    }

    public List<CursoOnline> filtrar(Boolean certificado, Double precioMax) {

        List<CursoOnline> filtrados = new ArrayList<>();

        for (CursoOnline c : cursos) {

            boolean cumple = true;

            if (certificado != null) {
                cumple = cumple && (c.isCertificado() == certificado);
            }

            if (precioMax != null) {
                cumple = cumple && (c.getPrecioBase() <= precioMax);
            }

            if (cumple) {
                filtrados.add(c);
            }
        }

        return filtrados;
    }

    public CursoOnline actualizar(int id, CursoOnline datos) {

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == id) {

                datos.setId(id);
                cursos.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    public boolean eliminar(int id) {
        return cursos.removeIf(c -> c.getId() == id);
    }

    public CursoOnline buscarPorId(int id) {
        for (CursoOnline c : cursos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}