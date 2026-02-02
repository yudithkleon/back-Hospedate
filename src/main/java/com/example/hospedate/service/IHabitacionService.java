package com.example.hospedate.service;
import com.example.hospedate.model.Habitacion;
import java.util.List;

public interface IHabitacionService {
    Habitacion crear(Habitacion habitacion);
    List<Habitacion> listar();
    Habitacion buscarPorId(Long id);
    Habitacion actualizar(Long id, Habitacion habitacion);
    void eliminar(Long id);
}