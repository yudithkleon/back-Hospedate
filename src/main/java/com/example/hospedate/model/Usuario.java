package com.example.hospedate.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    @JsonProperty("tipo_doc")
    private String tipoDoc;

    @NotBlank
    @JsonProperty("numero_doc")
    private String numeroDoc;

    @Email
    @Column(unique = true)
    @NotBlank
    private String correo;

    private String telefono;

    @Column(columnDefinition = "LONGTEXT")
    private String foto;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
   // @JsonBackReference("usuario-reserva")
    @JsonIgnoreProperties("usuario")
    private List<Reserva> reservas;

    public enum Rol {
        ADMIN, CLIENTE
    }

        public Usuario(){

        }

    public Usuario(Long idUsuario, String nombre, String apellido, String tipoDoc, String numeroDoc, String correo, String telefono, String contrasena, Rol rol, List<Reserva> reservas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rol = rol;
        this.reservas = reservas;
    }

    // getters y setters


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }


    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}


    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
