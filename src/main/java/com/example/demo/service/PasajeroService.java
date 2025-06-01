package com.example.demo.service;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.util.ReflectionMapper;
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

    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
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

    public Optional<PasajeroDTO> save(PasajeroDTO pasajeroDTO) {
        Pasajero pasajero = pasajeroMapper.toEntity(pasajeroDTO);
        pasajeroDTO = pasajeroMapper.toDto(pasajeroRepository.save(pasajero));
        return Optional.ofNullable(pasajeroDTO);
    }

    public void deleteById(Long id) {
        pasajeroRepository.deleteById(id);
    }
}