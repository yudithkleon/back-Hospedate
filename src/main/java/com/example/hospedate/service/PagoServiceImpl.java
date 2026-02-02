package com.example.hospedate.service;

import com.example.hospedate.dto.PagoRequestDTO;
import com.example.hospedate.dto.PagoResponseDTO;
import com.example.hospedate.model.Pago;
import com.example.hospedate.model.Reserva;
import com.example.hospedate.model.ServiciosAdicionales;
import com.example.hospedate.repository.PagoRepository;
import com.example.hospedate.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class PagoServiceImpl implements IPagoService {
    private final PagoRepository pagoRepo;
    private final ReservaRepository reservaRepo;

    public PagoServiceImpl(PagoRepository pagoRepo, ReservaRepository reservaRepo) {
        this.pagoRepo = pagoRepo;
        this.reservaRepo = reservaRepo;
    }

    @Override
    public PagoResponseDTO registrarPago(PagoRequestDTO dto) {

        if (pagoRepo.existsByReservaIdReserva(dto.getIdReserva())) {
            throw new RuntimeException("La reserva ya tiene pago");
        }

        Reserva reserva = reservaRepo.findById(dto.getIdReserva())
                .orElseThrow(() -> new RuntimeException("Reserva no existe"));

        BigDecimal total = calcularTotal(reserva);

        BigDecimal saldo = total.subtract(dto.getMontoPagado());

        Pago pago = new Pago();
        pago.setReserva(reserva);
        pago.setMontoTotal(total);
        pago.setMontoPagado(dto.getMontoPagado());
        pago.setSaldoPendiente(saldo);
        pago.setMetodo(Pago.MetodoPago.valueOf(dto.getMetodo()));
        pago.setMoneda("COP");

        pagoRepo.save(pago);

        if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
            reserva.setEstado(Reserva.EstadoReserva.CONFIRMADA);
        }

        return map(pago);
    }

    private BigDecimal calcularTotal(Reserva reserva) {
        // noches * precio + servicios
        BigDecimal base = BigDecimal.valueOf(reserva.getFechaReserva().getNoches())
                .multiply(reserva.getHabitacion().getPrecioPorNoche());

        BigDecimal servicios = reserva.getServicios().stream()
                .map(ServiciosAdicionales::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return base.add(servicios);
    }

    private PagoResponseDTO map(Pago pago) {
        PagoResponseDTO dto = new PagoResponseDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setMontoTotal(pago.getMontoTotal());
        dto.setMontoPagado(pago.getMontoPagado());
        dto.setSaldoPendiente(pago.getSaldoPendiente());
        dto.setMetodo(pago.getMetodo().name());
        dto.setMoneda(pago.getMoneda());
        return dto;
    }

    @Override
    public PagoResponseDTO obtenerPorReserva(Long idReserva) {
        Pago pago = pagoRepo.findByReservaIdReserva(idReserva)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        return map(pago);
    }

    @Override
    public PagoResponseDTO actualizarPago(Long idPago, PagoRequestDTO dto) {

        Pago pago = pagoRepo.findById(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        Reserva reserva = pago.getReserva();

        // Recalcular total real desde backend
        BigDecimal total = calcularTotal(reserva);

        BigDecimal montoPagado = dto.getMontoPagado();
        BigDecimal saldo = total.subtract(montoPagado);

        if (montoPagado.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("El monto pagado no puede ser negativo");
        }

        if (montoPagado.compareTo(total) > 0) {
            throw new RuntimeException("El monto pagado no puede ser mayor al total");
        }

        pago.setMontoTotal(total);
        pago.setMontoPagado(montoPagado);
        pago.setSaldoPendiente(saldo);

        if (dto.getMetodo() != null) {
            pago.setMetodo(Pago.MetodoPago.valueOf(dto.getMetodo()));
        }

        pagoRepo.save(pago);

        // Actualizar estado reserva
        if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
            reserva.setEstado(Reserva.EstadoReserva.CONFIRMADA);
        } else {
            reserva.setEstado(Reserva.EstadoReserva.ACTIVA);
        }

        return map(pago);
    }

}
