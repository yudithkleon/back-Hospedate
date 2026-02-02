package com.example.hospedate.controller;

import com.example.hospedate.dto.ReservRequestDTO;
import com.example.hospedate.dto.ReservaUpdateDTO;
import com.example.hospedate.dto.ServiciosAdcionalesDTO;
import com.example.hospedate.model.Reserva;
import com.example.hospedate.service.IReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reservas")

public class ReservaController {
    private final IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PostMapping
    public Reserva crear(@Valid @RequestBody ReservRequestDTO dto) {
        return reservaService.crear(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable Long id) {
        return reservaService.buscarPorId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Long id, @Valid @RequestBody ReservaUpdateDTO dto) {
        return reservaService.actualizar(id, dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PutMapping("/{id}/servicios")
    public Reserva agregarServicios(
            @PathVariable Long id,
            @RequestBody ServiciosAdcionalesDTO dto) {
        return reservaService.agregarServicios(id, dto.getIdsServicios());
    }

}
