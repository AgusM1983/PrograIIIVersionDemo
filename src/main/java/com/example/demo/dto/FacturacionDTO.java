package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionDTO {
    private Long reservaId;
    private String numeroHabitacion;
    private String tipoHabitacion;
    private int cantidadDias;
    private Double precioBase;
    private List<CostoServicioDTO> serviciosHabitacion;
    private List<ConsumoServAdicionalDTO> serviciosAdicionales;
    private Double subtotal;
    private Double total;

}
