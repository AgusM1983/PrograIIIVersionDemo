package com.example.demo.service;

import com.example.demo.dto.PasajeroCrearDTO;
import com.example.demo.dto.PasajeroDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.noIdenticos.PasajeroCrearMapper;
import com.example.demo.mapper.util.ReflectionMapper;
import com.example.demo.model.Empleado;
import com.example.demo.model.Pasajero;
import com.example.demo.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;
    private final PasajeroCrearMapper pasajeroCrearMapper;

    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository,
                           PasajeroMapper pasajeroMapper,
                           PasajeroCrearMapper pasajeroCrearMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
        this.pasajeroCrearMapper = pasajeroCrearMapper;
    }

    public List<PasajeroDTO> findAll() {
        return
                pasajeroRepository.findAll()
                        .stream().map(p->pasajeroMapper.toDto(p)).toList();
    }

    public Optional<PasajeroDTO> findById(Long id) {
        PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.findById(id).get());
        return Optional.ofNullable(pasajeroDTO);
    }


    public Optional<PasajeroDTO> save(PasajeroCrearDTO pasajeroCrearDTO) {
        Pasajero pasajero = pasajeroCrearMapper.toEntity(pasajeroCrearDTO);
        PasajeroDTO pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.save(pasajero));
        return Optional.ofNullable(pasajeroDTO);
    }

    public void deleteById(Long id) {
        pasajeroRepository.deleteById(id);
    }
}