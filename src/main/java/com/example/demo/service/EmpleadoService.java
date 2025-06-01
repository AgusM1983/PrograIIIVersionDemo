package com.example.demo.service;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.mapper.EmpleadoMapper;
import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    public List<EmpleadoDTO> findAll() {
        return empleadoRepository.findAll()
                .stream().map(p->empleadoMapper.toDto(p)).toList();
    }

    public Optional<EmpleadoDTO> findById(Long id) {
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleadoRepository.findById(id).get());
        return Optional.ofNullable(empleadoDTO);
    }

    public Optional<EmpleadoDTO> save(EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        empleadoDTO = empleadoMapper.toDto(empleadoRepository.save(empleado));
        return Optional.ofNullable(empleadoDTO);
    }

    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }
}