package com.example.demo.dto;

import com.example.demo.model.Pasajero;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.model.enums.EstadoPasajero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasajeroDTO {

    private Long id;
    private Usuario usuario;
    private EstadoPasajero estado;
    private List<Reserva> reservas;

}

