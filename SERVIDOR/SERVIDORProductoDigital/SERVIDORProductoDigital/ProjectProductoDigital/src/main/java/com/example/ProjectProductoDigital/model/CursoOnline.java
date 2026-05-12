package com.example.ProjectProductoDigital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CURSOONLINE")
public class CursoOnline {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PRECIO_BASE")
    private double precioBase;

    @Column(name = "TAMANO_MB")
    private double tamanoMB;

    @Column(name = "DURACION_HORAS")
    private int duracionHoras;

    @Column(name = "CERTIFICADO")
    private boolean certificado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDPLATAFORMA", nullable = false)
    private PlataformaProveedora plataformaProveedora;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "FECHA_CERTIFICADO")
    private LocalDateTime fechaCertificado;

    public CursoOnline() {}

    public CursoOnline(int id, String nombre, double precioBase, double tamanoMB,
                       int duracionHoras, boolean certificado,
                       PlataformaProveedora plataformaProveedora,
                       LocalDateTime fechaCertificado) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.tamanoMB = tamanoMB;
        this.duracionHoras = duracionHoras;
        this.certificado = certificado;
        this.plataformaProveedora = plataformaProveedora;
        this.fechaCertificado = fechaCertificado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public double getTamanoMB() { return tamanoMB; }
    public void setTamanoMB(double tamanoMB) { this.tamanoMB = tamanoMB; }

    public int getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(int duracionHoras) { this.duracionHoras = duracionHoras; }

    public boolean isCertificado() { return certificado; }
    public void setCertificado(boolean certificado) { this.certificado = certificado; }

    public PlataformaProveedora getPlataformaProveedora() { return plataformaProveedora; }
    public void setPlataformaProveedora(PlataformaProveedora plataformaProveedora) {
        this.plataformaProveedora = plataformaProveedora;
    }

    public LocalDateTime getFechaCertificado() { return fechaCertificado; }
    public void setFechaCertificado(LocalDateTime fechaCertificado) {
        this.fechaCertificado = fechaCertificado;
    }

    public static class Builder {
        private int id;
        private String nombre;
        private double precioBase;
        private double tamanoMB;
        private int duracionHoras;
        private boolean certificado;
        private PlataformaProveedora plataformaProveedora;
        private LocalDateTime fechaCertificado;

        public Builder id(int id) { this.id = id; return this; }
        public Builder nombre(String nombre) { this.nombre = nombre; return this; }
        public Builder precioBase(double precioBase) { this.precioBase = precioBase; return this; }
        public Builder tamanoMB(double tamanoMB) { this.tamanoMB = tamanoMB; return this; }
        public Builder duracionHoras(int duracionHoras) { this.duracionHoras = duracionHoras; return this; }
        public Builder certificado(boolean certificado) { this.certificado = certificado; return this; }
        public Builder plataforma(PlataformaProveedora plataforma) { this.plataformaProveedora = plataforma; return this; }
        public Builder fecha(LocalDateTime fecha) { this.fechaCertificado = fecha; return this; }

        public CursoOnline build() {
            return new CursoOnline(id, nombre, precioBase, tamanoMB,
                    duracionHoras, certificado, plataformaProveedora, fechaCertificado);
        }
    }
}
