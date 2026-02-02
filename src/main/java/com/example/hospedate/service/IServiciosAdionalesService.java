package com.example.hospedate.service;

import com.example.hospedate.model.ServiciosAdicionales;

import java.util.List;

public interface IServiciosAdionalesService {
    ServiciosAdicionales crear(ServiciosAdicionales servicio);
    List<ServiciosAdicionales> listar();
    ServiciosAdicionales buscarPorId(Long id);
    ServiciosAdicionales actualizar(Long id, ServiciosAdicionales servicio);
    void eliminar(Long id);
}
