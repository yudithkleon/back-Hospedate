package com.example.hospedate.repository;

import com.example.hospedate.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByReservaIdReserva(Long idReserva);

    boolean existsByReservaIdReserva(Long idReserva);
}
