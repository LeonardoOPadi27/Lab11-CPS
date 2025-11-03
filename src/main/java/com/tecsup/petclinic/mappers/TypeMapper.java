package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.entities.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {

    public Type mapToEntity(TypeDTO dto) {
        if (dto == null) return null;
        return new Type(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getActive(),
                dto.getSizeCategory(),
                dto.getAverageLifespan(),
                dto.getCareLevel()
        );
    }

    public TypeDTO mapToDto(Type entity) {
        if (entity == null) return null;
        return new TypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getActive(),
                entity.getSizeCategory(),
                entity.getAverageLifespan(),
                entity.getCareLevel()
        );
    }
}
