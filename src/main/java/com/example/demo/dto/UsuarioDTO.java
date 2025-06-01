package com.example.demo.dto;

import com.example.demo.model.Credenciales;
import com.example.demo.model.Empleado;
import com.example.demo.model.Pasajero;
import com.example.demo.model.Usuario;
import com.example.demo.model.embeddable.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Direccion direccion;
    private String permisos; // administrador, empleado, pasajero
    private boolean cuentaNoExpirada = true;
    private boolean cuentaNoBloqueada = true;
    private boolean credencialesNoExpiradas = true;
    private boolean activo = true;

}