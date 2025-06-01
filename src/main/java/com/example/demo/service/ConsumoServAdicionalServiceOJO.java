package com.example.demo.service;


import com.example.demo.model.ConsumoServAdicional;
import com.example.demo.repository.ConsumoServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoServAdicionalServiceOJO {

    @Autowired
    ConsumoServAdicionalRepository consumoServAdicionalRepository;

    public List<ConsumoServAdicional> findByReservaId(Long reservaId) {
        return consumoServAdicionalRepository.findByReservaId(reservaId);
    }

    public ConsumoServAdicional save(ConsumoServAdicional consumo) {
        return consumoServAdicionalRepository.save(consumo);
    }

    public Optional<ConsumoServAdicional> findById(Long id) {
        return consumoServAdicionalRepository.findById(id);
    }

    public void deleteById(Long id) {
        consumoServAdicionalRepository.deleteById(id);
    }
}
