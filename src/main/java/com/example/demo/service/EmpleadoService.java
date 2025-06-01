package com.example.demo.service;

import com.example.demo.dto.EmpleadoCrearDTO;
import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.mapper.noIdenticos.EmpleadoCrearMapper;
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
    private final EmpleadoCrearMapper empleadoCrearMapper;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository
            , EmpleadoMapper empleadoMapper
            ,EmpleadoCrearMapper empleadoCrearMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.empleadoCrearMapper = empleadoCrearMapper;
    }

    public List<EmpleadoDTO> findAll() {
        return empleadoRepository.findAll()
                .stream().map(p->empleadoMapper.toDto(p)).toList();
    }

    public Optional<EmpleadoDTO> findById(Long id) {
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleadoRepository.findById(id).get());
        return Optional.ofNullable(empleadoDTO);
    }

    public Optional<EmpleadoDTO> save(EmpleadoCrearDTO empleadoDTO) {
        Empleado empleado = empleadoCrearMapper.toEntity(empleadoDTO);
        EmpleadoDTO empleadoDTO2 = empleadoMapper.toDto(empleadoRepository.save(empleado));
        return Optional.ofNullable(empleadoDTO2);
    }

    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }
}