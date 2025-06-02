package com.example.demo.controller;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;
    @Autowired
    private final HabitacionMapper habitacionMapper;

    @GetMapping
    public List<HabitacionDTO> getAllHabitaciones() {
        List <HabitacionDTO> habitaciones =
                habitacionService.findAll().stream().
                        map(h-> habitacionMapper.toDto(h))
                        .toList();
        return habitaciones;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> getHabitacionById(@PathVariable Long id) {
        return habitacionService.findById(id)
                .map(habitacionMapper::toDto)       // Convertir la entidad a DTO
                .map(ResponseEntity::ok)            // Devolver 200 OK con el DTO
                .orElse(ResponseEntity.notFound().build()); // 404 si no existe
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<HabitacionDTO> getHabitacionByNumero(@PathVariable String numero) {
        return habitacionService.findByNumeroHabitacion(numero)
                .map(habitacionMapper::toDto)       // Convertir entidad → DTO
                .map(ResponseEntity::ok)            // Devolver 200 OK con el DTO
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
    }


    @GetMapping("/estado/{estado}")
    public List<HabitacionDTO> getHabitacionesByEstado(@PathVariable String estado) {
        return habitacionService.findByEstado(estado)
                .stream().map(h->habitacionMapper.toDto(h)).toList();
    }

    @GetMapping("/tipo/{tipo}")
    public List<HabitacionDTO> getHabitacionesByTipo(@PathVariable String tipo) {
        return habitacionService.findByTipoHabitacion(tipo)
                .stream().map(h->habitacionMapper.toDto(h)).toList();
    }

    @PostMapping
    public ResponseEntity<?> createHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        HabitacionDTO habitacionDTO1 = habitacionService.save(habitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacionDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHabitacion(@PathVariable Long id,
                                              @Valid @RequestBody HabitacionDTO habitacionDetailsDTO,
                                              BindingResult bindingResult) {
        // Validar el DTO
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }


        Optional<Habitacion> habitacion = habitacionService.findById(id);
        if (habitacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Habitacion destino = habitacion.get();
        Habitacion origen = habitacionMapper.toEntity(habitacionDetailsDTO);
        ReflectionMapper.actualizarCamposNoNulos(origen, destino);
        HabitacionDTO habitacionSalvadaDTO = habitacionMapper.toDto(destino);
        habitacionService.save(habitacionSalvadaDTO);
        return ResponseEntity.ok(habitacionSalvadaDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

/*
    @GetMapping("/{id}/costo")
    public ResponseEntity<?> calcularCostoHabitacion(@PathVariable Long id) {
        try {
            // 1. Obtener la habitación por ID
            Habitacion habitacion = habitacionService.findById(id).get();
            // 2. Calcular el costo totla de la habitacion usando el servicio
            // el Servicio habitacionService.calcualrCostoTotal hace lo siguiente:
            // recibe una entidad Habitacion (saca su id y su tipo)
                // CostoBase = con el tipo en la tabla costoHabitacion saca cuanto sale la habitacion base
                // Listado de Servicios de la habitacion = con la id en la tabla habitacion_servicios ve cuales son los servicios que tiene activos esa habitacion y genera una List
                // CostoDeLosServicios =con la List de servicios que se genero tiene esa habitacion en la tabla servicios suma el costo de cada servicio
                // Costo total = CostoBase + CostoDeLosServicios

            Double costoTotal = habitacionService.calcularCostoTotal(habitacion);
            // 3. Devolver la respuesta con el costo
            return ResponseEntity.ok(costoTotal);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al calcular el costo de la habitación");
        }
    }
 */
}
