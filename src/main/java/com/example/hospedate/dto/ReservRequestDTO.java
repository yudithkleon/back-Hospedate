package com.example.hospedate.dto;

import java.time.LocalDateTime;

public class ReservRequestDTO {
    private Long idUsuario;
    private Long idHabitacion;
    private String estado;
    private String notas;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    //getters y setters
    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNotas() {
        return notas;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }
    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }


}
