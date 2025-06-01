package com.example.demo.controller.impuros;

import com.example.demo.service.ConsumoServAdicionalServiceOJO;
import com.example.demo.service.ReservaService;
import com.example.demo.service.ServAdicionalServiceOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumos-servicios-adicionales")
public class ConsumoServAdicionalController {

    @Autowired
    private ConsumoServAdicionalServiceOJO consumoServAdicionalServiceOJO;
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ServAdicionalServiceOJO servAdicionalServiceOJO;


/*
    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<ConsumoServAdicionalDTO>> listarPorReserva(@PathVariable Long reservaId) {
        List<ConsumoServAdicionalDTO> dtos = consumoServAdicionalService.findByReservaId(reservaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
*/

    // Si se cambia el modelo a CostoServicioAdiciona (id,nombre,costo) | Reserva atributo nuevo (Map<ServiciosAdicionales, Boolean>) se soluciona.
    // Ver el tema de la fecha 1 y fecha 2 de Costo Serv Adicional si es para registrar el registro de fechas de los costos combiene simplemente guardar:
        // Una entidad Presupuesto, no tiene DTO se construye con metodo (para guardar que se presupuesto cuando confirma reserva)
        // Una entidad Facturacion, no tiene DTO se consturye con metodo (para guardar lo que se cobro)
        // Una entidad historicoCostoServicioAdicional, no tiene DTO que cuando se modifica el costo del servicio guarda fecha idCosto monto viejo
  /*
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ConsumoServAdicionalDTO dto) {
        Reserva reserva = reservaService.findById(dto. getReservaId()) // problema de diseño de la entidad no hay fk de reserva
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        ServAdicional adicional = servAdicionalService.findById(dto. getServAdicionalId()) // problema de diseño de la entidad no hay fk de Servicio
                .orElseThrow(() -> new RuntimeException("Servicio adicional no encontrado"));

        ConsumoServAdicional consumo = new ConsumoServAdicional();
        consumo.setReserva(reserva);
        consumo.setServAdicional(adicional);
        consumo.setFechaConsumo(dto.getFechaConsumo());
        consumo.setCantidad(dto.getCantidad() != null ? dto.getCantidad() : 1);

        ConsumoServAdicional guardado = consumoServAdicionalService.save(consumo);
        return ResponseEntity.ok(toDTO(guardado));
    }
*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        consumoServAdicionalServiceOJO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

/*    // Mapper simple
        private ConsumoServAdicionalDTO toDTO(ConsumoServAdicional consumo) {
        ConsumoServAdicionalDTO dto = new ConsumoServAdicionalDTO();
        dto.setId(consumo.getId());
        dto.setReservaId(consumo.getReserva().getId());
        dto.setServAdicionalId(consumo.getServAdicional().getId());
        dto.setFechaConsumo(consumo.getFechaConsumo());
        dto.setCantidad(consumo.getCantidad());
        return dto;
    }

 */
}
