package com.example.demo.controller;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Empleado;
import com.example.demo.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Long id) {
        Optional<EmpleadoDTO> empleado = empleadoService.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpleadoDTO createEmpleado(@RequestBody EmpleadoDTO empleado) {
        return empleadoService.save(empleado).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDetails) {
        Optional<EmpleadoDTO> empleado = empleadoService.findById(id);
        if (empleado.isPresent()) {
            EmpleadoDTO updatedEmpleado = empleado.get();
            ReflectionMapper.actualizarCamposNoNulos(empleadoDetails,updatedEmpleado);// Actualizar campos aquí
            return ResponseEntity.ok(empleadoService.save(updatedEmpleado).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}