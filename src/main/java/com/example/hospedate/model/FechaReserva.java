package com.example.hospedate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "fecha_reserva")
public class FechaReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFechas;

    @OneToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonBackReference("reserva-fecha")
    private Reserva reserva;

    @NotNull
    @JsonProperty("checkIn")
    @Column(name = "check_in")
    private LocalDateTime check_in;

    @NotNull
    @JsonProperty("checkOut")
    @Column(name = "check_out")
    private LocalDateTime check_out;

    private Integer noches;

    //getters y setters

    public Long getIdFechas() {
        return idFechas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setIdFechas(Long idFechas) {
        this.idFechas = idFechas;
    }

    public LocalDateTime getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDateTime check_in) {
        this.check_in = check_in;
    }

    public LocalDateTime getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDateTime check_out) {
        this.check_out = check_out;
    }

    public Integer getNoches() {
        return noches;
    }

    public void setNoches(Integer noches) {
        this.noches = noches;
    }
}
