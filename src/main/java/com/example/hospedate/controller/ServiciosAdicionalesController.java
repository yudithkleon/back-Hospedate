package com.example.hospedate.controller;

import com.example.hospedate.model.ServiciosAdicionales;
import com.example.hospedate.service.IServiciosAdionalesService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServiciosAdicionalesController {
    private final IServiciosAdionalesService service;

    public ServiciosAdicionalesController(IServiciosAdionalesService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ServiciosAdicionales crear(@Valid @RequestBody ServiciosAdicionales servicio) {
        return service.crear(servicio);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping
    public List<ServiciosAdicionales> listar() {
        return service.listar();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ServiciosAdicionales buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ServiciosAdicionales actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServiciosAdicionales servicio) {
        return service.actualizar(id, servicio);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
