package com.example.demo.service;

import com.example.demo.model.CostoServAdicional;
import com.example.demo.model.ServAdicional;
import com.example.demo.repository.CostoServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CostoServAdicionalService {


    @Autowired
    private CostoServAdicionalRepository costoServAdicionalRepository;

    public List<CostoServAdicional> findAll() {
        return costoServAdicionalRepository.findAll();
    }

    public Optional<CostoServAdicional> findById(Long id) {
        return costoServAdicionalRepository.findById(id);
    }

    public CostoServAdicional save(CostoServAdicional costo) {
        return costoServAdicionalRepository.save(costo);
    }

    public void deleteById(Long id) {
        costoServAdicionalRepository.deleteById(id);
    }

    /**
     * Devuelve el costo vigente para el servicio adicional en la fecha dada
     */
    public Optional<CostoServAdicional> findVigente(ServAdicional servAdicional, LocalDate fecha) {
        return costoServAdicionalRepository
                .findByServAdicionalAndFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(
                        servAdicional, fecha, fecha
                );
    }

    /**
     * Verifica si existe solapamiento de fechas para un servicio adicional.
     * idIgnorar permite excluir un registro del chequeo (para edición).
     */
    public boolean existeSolapamiento(ServAdicional servAdicional, LocalDate desde, LocalDate hasta, Long idIgnorar) {
        List<CostoServAdicional> existentes = costoServAdicionalRepository
                .findAll().stream()
                .filter(c -> c.getServAdicional().getId().equals(servAdicional.getId()))
                .filter(c -> idIgnorar == null || !c.getId().equals(idIgnorar))
                .toList();
        // Devuelve true si algún rango existente se solapa con el nuevo
        return existentes.stream().anyMatch(c ->
                (desde.isBefore(c.getFechaHasta()) && hasta.isAfter(c.getFechaDesde())) ||
                        desde.equals(c.getFechaDesde()) || hasta.equals(c.getFechaHasta())
        );
    }
}
