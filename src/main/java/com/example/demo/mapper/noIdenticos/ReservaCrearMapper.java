package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.*;
import com.example.demo.mapper.HabitacionMapper;
import com.example.demo.mapper.PasajeroMapper;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.*;
import com.example.demo.service.HabitacionService;
import com.example.demo.service.PasajeroService;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservaCrearMapper {

    private final PasajeroService pasajeroService;
    private final PasajeroMapper pasajeroMapper;
    private final HabitacionService habitacionService;
    private final HabitacionMapper habitacionMapper;

    @Autowired
    public ReservaCrearMapper(PasajeroService pasajeroService, PasajeroMapper pasajeroMapper, HabitacionService habitacionService, HabitacionMapper habitacionMapper) {
        this.pasajeroService = pasajeroService;
        this.pasajeroMapper = pasajeroMapper;
        this.habitacionService = habitacionService;
        this.habitacionMapper = habitacionMapper;
    }

    public ReservaCrearDTO toDto(Reserva model) {
        if (model == null) {
            return null;
        }
        ReservaCrearDTO dto = new ReservaCrearDTO();
        dto.setId(model.getId());
        dto.setFechaInicio(model.getFechaInicio());
        dto.setFechaFin(model.getFechaFin());
        dto.setHabitacionId(model.getHabitacion().getId());
        dto.setPasajeroId(model.getPasajero().getId());
        dto.setEstado(model.getEstado());
        dto.setObservaciones(model.getObservaciones());
        return dto;
    }

    public Reserva toEntity(ReservaCrearDTO dto) {

        if (dto == null) {
            return null;
        }
        Optional<PasajeroDTO> pasajeroDTO = pasajeroService.findById(dto.getPasajeroId());
        Optional<Habitacion> habitacion = habitacionService.findById(dto.getHabitacionId());
        if(pasajeroDTO.isEmpty() || habitacion.isEmpty()){
            return null;
        }
        Pasajero pasajeroModel = pasajeroMapper.toEntity(pasajeroDTO.get());
        Reserva model = new Reserva();
        model.setId(dto.getId());
        model.setEstado(dto.getEstado());
        model.setFechaFin(dto.getFechaFin());
        model.setFechaInicio(dto.getFechaInicio());
        model.setHabitacion(habitacion.get());
        model.setPasajero(pasajeroModel);
        model.setObservaciones(dto.getObservaciones());
        return model;
    }
}
