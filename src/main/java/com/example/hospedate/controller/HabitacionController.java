package com.example.hospedate.controller;

import com.example.hospedate.model.Habitacion;
import com.example.hospedate.service.IHabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final IHabitacionService habitacionService;

    public HabitacionController(IHabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @Operation(
            summary = "Crear Habitación Admin",
            description = "Crea la habitacion desde el Admin, retorna los datos de la habitación"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creada la habitación"),
            @ApiResponse(responseCode = "401", description = "Error en el ingreso de los datos")
    })
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PostMapping
    public Habitacion crear(@RequestBody Habitacion habitacion) {
        return habitacionService.crear(habitacion);
    }

    //Todos
    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }


    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/{id}")
    public Habitacion buscar(@PathVariable Long id) {
        return habitacionService.buscarPorId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @PutMapping("/{id}")
    public Habitacion actualizar(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return habitacionService.actualizar(id, habitacion);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
    }
}

