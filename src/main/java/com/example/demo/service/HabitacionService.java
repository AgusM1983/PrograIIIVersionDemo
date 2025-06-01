package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.model.CostoHabitacion;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Costo_Servicio;
import com.example.demo.model.Reserva;
import com.example.demo.model.enums.ServicioEnum;
import com.example.demo.model.enums.TipoHabitacion;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.Costo_ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;
    //    private final Costo_ServicioRepository costoServicioRepository;
//    private final CostoHabitacionService costoHabitacionService;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository,
                             HabitacionMapper habitacionMapper) {
        this.habitacionRepository = habitacionRepository;
        this.habitacionMapper = habitacionMapper;
    }

    // Métodos básicos de CRUD
    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> findById(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion save(HabitacionDTO habitacion) {
        return habitacionRepository.save(habitacionMapper.toEntity(habitacion));
    }

    public void deleteById(Long id) {
        habitacionRepository.deleteById(id);
    }

    // Métodos de búsqueda específicos
    public List<Habitacion> findByEstado(String estado) {
        return habitacionRepository.findByEstado(estado);
    }

    public List<Habitacion> findByTipoHabitacion(String tipo) {
        return habitacionRepository.findByTipoHabitacion(tipo);
    }

    public Optional<Habitacion> findByNumeroHabitacion(String numero) {
        return Optional.ofNullable(habitacionRepository.findByNumeroHabitacion(numero));
    }

}