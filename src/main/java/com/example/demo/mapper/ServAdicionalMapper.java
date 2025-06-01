package com.example.demo.mapper;


import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.ServAdicional;
import com.example.demo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    public interface ServAdicionalMapper {
        ServAdicional toEntity(ServAdicionalDTO dto);
        ServAdicionalDTO toDTO(ServAdicional entity);
    }

