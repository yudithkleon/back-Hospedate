package com.example.hospedate.service;

import com.example.hospedate.model.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUsuarioService {
    Usuario crear(Usuario usuario);
    List<Usuario> listar();
    Usuario buscarPorId(Long id);
   Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    Usuario buscarPorCorreo(String correo);

    Usuario actualizando(Long id, String nombre, String apellido, String correo, String telefono, MultipartFile foto);
}
