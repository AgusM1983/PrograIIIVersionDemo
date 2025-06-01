package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoServAdicionalDTO {

    private Long id;
    @NotNull(message = "La cantidad no puede ser nula")
    private String nombreServicioAdicional;
    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;
    @NotNull(message = "La fecha de consumo no puede ser nula")
    private LocalDate fechaConsumo;
    private Double precioUnitario;     // el precio de ese servicio en la fecha
    private Double precioTotal;


}
