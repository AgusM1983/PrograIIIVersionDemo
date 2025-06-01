package com.example.demo.service;


import com.example.demo.model.ServAdicional;
import com.example.demo.model.enums.ServAdicionalEnum;
import com.example.demo.repository.ServAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServAdicionalServiceOJO {

    private final ServAdicionalRepository servAdicionalRepository;

    @Autowired
    public ServAdicionalServiceOJO(ServAdicionalRepository servAdicionalRepository) {
        this.servAdicionalRepository = servAdicionalRepository;
    }

    public List<ServAdicional> findAll() {
        return servAdicionalRepository.findAll();
    }

    public Optional<ServAdicional> findById(Long id) {
        return servAdicionalRepository.findById(id);
    }

    public ServAdicional save(ServAdicional servicioAdicional) {
        return servAdicionalRepository.save(servicioAdicional);
    }

    public void deleteById(Long id) {
        servAdicionalRepository.deleteById(id);
    }

    public Optional<ServAdicional> findByNombre(String nombre) {
        ServAdicionalEnum enumNombre = ServAdicionalEnum.valueOf(nombre.toUpperCase());
        return servAdicionalRepository.findByNombre(enumNombre);
    }


}
