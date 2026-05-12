package com.example.ProjectProductoDigital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLATAFORMA")
public class PlataformaProveedora {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "URL")
    private String url;

    @Column(name = "COSTO_SUSCRIPCION")
    private double costoSuscripcion;

    @Column(name = "SOPORTE_24HORAS")
    private boolean soporte24Horas;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "FECHA_FUNDACION")
    private LocalDateTime fechaFundacion;

    @OneToMany(mappedBy = "plataformaProveedora", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CursoOnline> cursos = new ArrayList<>();

    public PlataformaProveedora() {
    }

    public PlataformaProveedora(int id, String nombre, String url,
                                double costoSuscripcion, boolean soporte24Horas,
                                LocalDateTime fechaFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.costoSuscripcion = costoSuscripcion;
        this.soporte24Horas = soporte24Horas;
        this.fechaFundacion = fechaFundacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public void setCostoSuscripcion(double costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    public boolean isSoporte24Horas() {
        return soporte24Horas;
    }

    public void setSoporte24Horas(boolean soporte24Horas) {
        this.soporte24Horas = soporte24Horas;
    }

    public LocalDateTime getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDateTime fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public List<CursoOnline> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoOnline> cursos) {
        this.cursos = cursos;
    }
}
