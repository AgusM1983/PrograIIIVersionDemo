package com.example.demo.mapper;

import com.example.demo.dto.ConsumoServAdicionalDTO;
import com.example.demo.model.ConsumoServAdicional;



    public interface ConsumoServAdicionalMapper {
        ConsumoServAdicional toEntity(ConsumoServAdicionalDTO dto);
        ConsumoServAdicionalDTO toDTO(ConsumoServAdicional entity);
    }


