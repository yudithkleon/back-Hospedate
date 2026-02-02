package com.example.hospedate.dto;

import java.math.BigDecimal;

public class PagoRequestDTO {
    private Long idReserva;
    private BigDecimal montoPagado;
    private String metodo;

    public Long getIdReserva() {
        return idReserva;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

}
