package com.example.hospedate.service;

import com.example.hospedate.model.ServiciosAdicionales;
import com.example.hospedate.repository.ServiciosAdicionalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosAdicionalesServiceImpl implements IServiciosAdionalesService {
    private final ServiciosAdicionalesRepository repository;

   public ServiciosAdicionalesServiceImpl(ServiciosAdicionalesRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiciosAdicionales crear(ServiciosAdicionales servicio) {
        return repository.save(servicio);
    }

    @Override
    public List<ServiciosAdicionales> listar() {
        return repository.findAll();
    }

    @Override
    public ServiciosAdicionales buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }

    @Override
    public ServiciosAdicionales actualizar(Long id, ServiciosAdicionales servicio) {
        ServiciosAdicionales existente = buscarPorId(id);
        existente.setNombre(servicio.getNombre());
        existente.setPrecio(servicio.getPrecio());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscarPorId(id));
    }
}
