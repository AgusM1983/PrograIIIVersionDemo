package com.example.demo.service;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.model.*;
import com.example.demo.model.enums.EstadoReserva;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }



    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll()
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public Optional<ReservaDTO> findById(Long id) {

        Reserva reserva = reservaRepository.findById(id).get();
        ReservaDTO reservaDTO = reservaMapper.toDto(reserva);
        return Optional.ofNullable(reservaDTO);
    }

    public Optional <ReservaDTO> save(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        return Optional.ofNullable(
                reservaMapper.toDto(reservaRepository.save(reserva))
        );
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> findByFechaInicioBetween(LocalDate inicio, LocalDate fin) {
        return reservaRepository.findByFechaInicioBetween(inicio, fin)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<ReservaDTO> findByPasajeroId(Long pasajeroId) {
        return reservaRepository.findByPasajeroId(pasajeroId)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<ReservaDTO> findByHabitacionId(Long habitacionId) {
        return reservaRepository.findByHabitacionId(habitacionId)
                .stream().map(r->reservaMapper.toDto(r))
                .toList();
    }

    public List<Long> obtenerHabitacionesReservadas(LocalDate fechaInicio, LocalDate fechaFin) { // me devuelve las id de las habitaciones reservadas entre esas fechas (independientemente del estado
        return reservaRepository.findHabitacionIdsReservadasEntreFechas(fechaInicio, fechaFin);
    }

    public List<ReservaDTO> findByEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado).stream()
                .map(r->reservaMapper.toDto(r)).toList();
    }
}