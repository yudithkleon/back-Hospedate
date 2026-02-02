package com.example.hospedate.service;

import com.example.hospedate.dto.PagoRequestDTO;
import com.example.hospedate.dto.PagoResponseDTO;

public interface IPagoService {
    PagoResponseDTO registrarPago(PagoRequestDTO dto);

    PagoResponseDTO obtenerPorReserva(Long idReserva);
    PagoResponseDTO actualizarPago(Long idPago, PagoRequestDTO dto);

}
