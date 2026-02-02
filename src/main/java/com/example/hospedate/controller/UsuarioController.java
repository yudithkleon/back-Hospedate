package com.example.hospedate.controller;

import com.example.hospedate.JwtUtil;
import com.example.hospedate.dto.UserDTO;
import com.example.hospedate.model.Usuario;
import com.example.hospedate.service.IUsuarioService;
import com.example.hospedate.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UsuarioServiceImpl userService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
     public Usuario crear(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
        /*return ResponseEntity.ok("Usuario registrado con éxito");*/
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENTE')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Usuario> actualizarPerfil(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String correo,
            @RequestParam String telefono,
            @RequestPart(required = false) MultipartFile foto
    ) {
        Usuario actualizado = usuarioService.actualizando(
                id, nombre, apellido, correo, telefono, foto
        );

        return ResponseEntity.ok(actualizado);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    //----Autenticación
    @Operation(
            summary = "Login de usuario",
            description = "Autentica usuario y retorna JWT + datos básicos"
    )
    @PostMapping("/login")
    public ResponseEntity<?> loginConDTO(@RequestBody UserDTO userDto) {

        UserDetails userDetails = userService.loadUserByUsername(userDto.getCorreo());
        if (!passwordEncoder.matches(userDto.getContrasena(), userDetails.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(userDetails);

        Usuario usuario = usuarioService.buscarPorCorreo(userDto.getCorreo());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuario", usuario);

        return ResponseEntity.ok(response);
/*
       if (userDetails != null && passwordEncoder.matches(userDto.getContrasena(), userDetails.getPassword())) {
            //String token = jwtUtil.generateToken(userDetails.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");*/
    }

}

