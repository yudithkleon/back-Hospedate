package com.example.hospedate.dto;

import java.math.BigDecimal;

public class PagoResponseDTO {
    private Long idPago;
    private BigDecimal montoTotal;
    private BigDecimal montoPagado;
    private BigDecimal saldoPendiente;
    private String metodo;
    private String moneda;

    //--

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setSaldoPendiente(BigDecimal saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public Long getIdPago() {
        return idPago;
    }

    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }


    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
