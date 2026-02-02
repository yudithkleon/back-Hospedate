package com.example.hospedate.service;

import com.example.hospedate.dto.ReservRequestDTO;
import com.example.hospedate.dto.ReservaUpdateDTO;
import com.example.hospedate.model.Reserva;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

public interface IReservaService {
    Reserva crear(ReservRequestDTO dto);
    List<Reserva> listar();
    Reserva buscarPorId(Long id);
    Reserva actualizar(Long id, @Valid ReservaUpdateDTO reserva);
    void eliminar(Long id);

    @Transactional
    Reserva agregarServicios(Long idReserva, List<Long> idsServicios);
}
