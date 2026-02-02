package com.example.hospedate.dto;

import com.example.hospedate.model.Reserva;

import java.time.LocalDateTime;

public class ReservaUpdateDTO {

    private Reserva.EstadoReserva estado;
    private String notas;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Reserva.EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(Reserva.EstadoReserva estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
