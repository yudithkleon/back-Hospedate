package com.example.hospedate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    //@JsonManagedReference("usuario-reserva")
    @JsonIgnoreProperties("reservas")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_habitacion", nullable = false)
    //@JsonManagedReference("habitacion-reserva")
    @JsonIgnoreProperties("reservas")
    private Habitacion habitacion;

    // Reserva ES la dueña de la relación
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    @JsonManagedReference("reserva-fecha")
    private FechaReserva fechaReserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;

    private String notas;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creadaEn;

    @ManyToMany
    @JoinTable(
            name = "reserva_servicios",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<ServiciosAdicionales> servicios;
    {
        servicios = new ArrayList<>();
    }
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    @JsonManagedReference("reserva-pago")
    private Pago pago;

    //  Enum (mejor en MAYÚSCULAS)
    public enum EstadoReserva {
        ACTIVA,
        PENDIENTE,
        CONFIRMADA,
        CANCELADA,
        FINALIZADA
    }


    public Reserva() {}

    // Inicializa fecha automáticamente
    @PrePersist
    public void prePersist() {
        this.creadaEn = LocalDateTime.now();
    }

    //  Sincroniza relación bidireccional
    public void setFechaReserva(FechaReserva fechaReserva) {
        this.fechaReserva = fechaReserva;
        if (fechaReserva != null) {
            fechaReserva.setReserva(this);
        }
    }

    // ===== GETTERS & SETTERS =====

    public Long getIdReserva() {
        return idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public FechaReserva getFechaReserva() {
        return fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getCreadaEn() {
        return creadaEn;
    }

    public List<ServiciosAdicionales> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServiciosAdicionales> servicios) {
        this.servicios = servicios;
    }
}
