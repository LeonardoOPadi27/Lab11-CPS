package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    // Fetch types by name
    List<Type> findByName(String name);

    // Fetch active types
    List<Type> findByActive(Boolean active);

    // Fetch types by size category
    List<Type> findBySizeCategory(String sizeCategory);

    @Override
    List<Type> findAll();
}
