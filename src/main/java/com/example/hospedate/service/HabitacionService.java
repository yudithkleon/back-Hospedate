package com.example.hospedate.service;

import com.example.hospedate.exception.ResourceNotFoundException;
import com.example.hospedate.model.Habitacion;
import com.example.hospedate.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService implements IHabitacionService{
    private HabitacionRepository habitacionRepository;

    public void HabitacionServiceImpl(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    public Habitacion crear(Habitacion habitacion) {

        // Regla de negocio: número único
        if (habitacionRepository.existsByNumero(habitacion.getNumero())) {
            throw new RuntimeException("Ya existe una habitación con ese número");
        }

        return habitacionRepository.save(habitacion);
    }

    @Override
    public List<Habitacion> listar() {
        return habitacionRepository.findAll();
    }

    @Override
    public Habitacion buscarPorId(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habitación no encontrada con id " + id)
                );
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion habitacion) {
        Habitacion existente = buscarPorId(id);
        existente.setNumero(habitacion.getNumero());
        existente.setTipo(habitacion.getTipo());
        existente.setCapacidad(habitacion.getCapacidad());
        existente.setPrecioPorNoche(habitacion.getPrecioPorNoche());
        existente.setUrlFoto((habitacion.getUrlFoto()));
        return habitacionRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Habitacion existente = buscarPorId(id);
        habitacionRepository.delete(existente);
    }
}
