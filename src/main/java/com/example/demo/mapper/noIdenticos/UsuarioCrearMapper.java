package com.example.demo.mapper.noIdenticos;

import com.example.demo.dto.PasajeroDTO;
import com.example.demo.dto.ReservaCrearDTO;
import com.example.demo.dto.UsuarioCrearDTO;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Pasajero;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.model.embeddable.Direccion;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioCrearMapper {


    public UsuarioCrearDTO toDto(Usuario model) {
        if (model == null) {
            return null;
        }
        UsuarioCrearDTO dto = new UsuarioCrearDTO();
        dto.setId(model.getId());
        dto.setDni(model.getDni());
        dto.setNombre(model.getNombre());
        dto.setApellido(model.getApellido());
        dto.setTelefono(model.getTelefono());
        dto.setEmail(model.getEmail());
        dto.setPermisos(model.getPermisos()); // administrador, empleado, pasajero
        //private Direccion direccion;
        dto.setCalle(model.getDireccion().getCalle());
        dto.setNumero(model.getDireccion().getNumero());
        dto.setCiudad(model.getDireccion().getCiudad());
        dto.setProvincia(model.getDireccion().getProvincia());
        dto.setCodigoPostal(model.getDireccion().getCodigoPostal());
        dto.setPais(model.getDireccion().getPais());
        return dto;
    }


    public Usuario toEntity(UsuarioCrearDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario model = new Usuario();
        model.setId(dto.getId());
        model.setDni(dto.getDni());
        model.setNombre(dto.getNombre());
        model.setApellido(dto.getApellido());
        model.setTelefono(dto.getTelefono());
        model.setEmail(dto.getEmail());
        model.setPermisos(dto.getPermisos()); // administrador, empleado, pasajero
        //private Direccion direccion;
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCiudad(dto.getCiudad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPais(dto.getPais());
        model.setDireccion(direccion);
        return model;
    }

}
