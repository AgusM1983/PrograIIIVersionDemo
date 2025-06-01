package com.example.demo.mapper;

import com.example.demo.dto.EmpleadoAccionReservaDTO;
import com.example.demo.model.Empleado;
import com.example.demo.model.EmpleadoAccionReserva;



    public interface EmpleadoAccionReservaMapper {
        EmpleadoAccionReserva toEntity(EmpleadoAccionReservaDTO dto);
        EmpleadoAccionReservaDTO toDTO(EmpleadoAccionReserva entity);
    }
