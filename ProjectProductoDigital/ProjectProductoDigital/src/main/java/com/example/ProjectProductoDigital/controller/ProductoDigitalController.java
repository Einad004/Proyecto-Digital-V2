package com.example.ProjectProductoDigital.controller;

import com.example.ProjectProductoDigital.model.ProductoDigital;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/productoDigital")
public class ProductoDigitalController {

    private static ProductoDigital pd = null;

    @RequestMapping(value = "/healthCheck")
    public String healthCheck() {
        return "Servicio Producto Digital Ok!";
    }
}
