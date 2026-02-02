package com.example.hospedate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_reserva", nullable = false, unique = true)
    @JsonBackReference("reserva-pago")
    private Reserva reserva;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodo;

    @Column(nullable = false)
    private BigDecimal montoTotal;

    @Column(nullable = false)
    private BigDecimal montoPagado;

    @Column(nullable = false)
    private BigDecimal saldoPendiente;

    private String moneda;

    public enum MetodoPago {
        EFECTIVO,
        TARJETA,
        TRANSFERENCIA
    }


    //getters y setters


    public Long getIdPago() {
        return idPago;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public String getMoneda() {
        return moneda;
    }

    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setSaldoPendiente(BigDecimal saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

}
