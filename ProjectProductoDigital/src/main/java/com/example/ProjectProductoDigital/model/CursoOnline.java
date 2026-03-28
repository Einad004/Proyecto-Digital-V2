package com.example.ProjectProductoDigital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoOnline extends ProductoDigital{
    private int duracionHoras;
    private boolean certificado;
}
