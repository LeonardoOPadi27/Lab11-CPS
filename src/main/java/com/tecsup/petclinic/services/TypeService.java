package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;


public interface TypeService {

    TypeDTO create(TypeDTO typeDTO);
    TypeDTO update(TypeDTO typeDTO);
    void delete(Integer id) throws TypeNotFoundException;
    TypeDTO findById(Integer id) throws TypeNotFoundException;
    List<TypeDTO> findByName(String name);
    List<Type> findByActive(Boolean active);
    List<Type> findAll();
}
