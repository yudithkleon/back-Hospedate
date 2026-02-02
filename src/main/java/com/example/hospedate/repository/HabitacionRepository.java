package com.example.hospedate.repository;

import com.example.hospedate.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    boolean existsByNumero(String numero);

}

