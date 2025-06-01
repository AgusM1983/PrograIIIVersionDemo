package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "consumos_servicio_adicional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoServAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "serv_adicional_id")
    private ServAdicional servAdicional;

    @Column(nullable = false)
    private LocalDate fechaConsumo;

    @Column(nullable = false)
    private Integer cantidad = 1; // Por defecto 1
}