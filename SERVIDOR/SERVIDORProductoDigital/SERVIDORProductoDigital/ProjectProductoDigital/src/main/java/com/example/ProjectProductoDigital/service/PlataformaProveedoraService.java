package com.example.ProjectProductoDigital.service;

import com.example.ProjectProductoDigital.model.PlataformaProveedora;
import com.example.ProjectProductoDigital.repositories.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaProveedoraService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    public String healthCheck() {
        return "Servicio de Plataformas Proveedoras operativo!";
    }

    public PlataformaProveedora crear(PlataformaProveedora plataforma) {
        return plataformaRepository.save(plataforma);
    }

    public List<PlataformaProveedora> listarTodo() {
        return plataformaRepository.listarTodasOrdenadas();
    }

    public PlataformaProveedora buscarPorId(int id) {
        return plataformaRepository.findById(id).orElse(null);
    }

    public List<PlataformaProveedora> filtrarPorNombre(String nombre) {
        return plataformaRepository.filtrarPorNombre(nombre);
    }

    public PlataformaProveedora buscarPorParametros(String nombre, String url) {
        List<PlataformaProveedora> resultados = plataformaRepository.buscarPorNombreYUrl(nombre, url);

        if (resultados.isEmpty()) {
            return null;
        }

        return resultados.get(0);
    }

    public PlataformaProveedora actualizar(int id, PlataformaProveedora datosActualizados) {
        return plataformaRepository.findById(id).map(plataformaExistente -> {
            datosActualizados.setId(id);
            return plataformaRepository.save(datosActualizados);
        }).orElse(null);
    }

    public boolean eliminar(int id) {
        if (plataformaRepository.existsById(id)) {
            plataformaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
