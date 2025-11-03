package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.TypeDTO;
import org.junit.jupiter.api.Test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testFindTypeById() {
        String NAME_EXPECTED = "cat";
        Integer ID = 1;

        TypeDTO type = null;

        try {
            type = this.typeService.findById(ID);
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }
        assertEquals(NAME_EXPECTED, type.getName());
    }

    @Test
    public void testFindTypeByName() {
        String FIND_NAME = "cat";
        int SIZE_EXPECTED = 1;

        List<TypeDTO> types = this.typeService.findByName(FIND_NAME);

        assertEquals(SIZE_EXPECTED, types.size());
    }

    @Test
    public void testFindTypeByActive() {
        Boolean ACTIVE = true;
        int MIN_SIZE_EXPECTED = 1;

        List<Type> types = this.typeService.findByActive(ACTIVE);

        assertTrue(types.size() >= MIN_SIZE_EXPECTED);
    }

    @Test
    public void testFindAllTypes() {
        int MIN_SIZE_EXPECTED = 1;

        List<Type> types = this.typeService.findAll();

        assertTrue(types.size() >= MIN_SIZE_EXPECTED);
    }

    @Test
    public void testCreateType() {
        String TYPE_NAME = "fish";
        String DESCRIPTION = "Small fish";
        Boolean ACTIVE = true;
        String SIZE_CATEGORY = "Small";
        Integer AVERAGE_LIFESPAN = 8;
        String CARE_LEVEL = "high";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(TYPE_NAME)
                .description(DESCRIPTION)
                .active(ACTIVE)
                .sizeCategory(SIZE_CATEGORY)
                .averageLifespan(AVERAGE_LIFESPAN)
                .careLevel(CARE_LEVEL)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);

        log.info("TYPE CREATED: " + newTypeDTO.toString());

        assertNotNull(newTypeDTO.getId());
        assertEquals(TYPE_NAME, newTypeDTO.getName());
        assertEquals(DESCRIPTION, newTypeDTO.getDescription());
        assertEquals(ACTIVE, newTypeDTO.getActive());
        assertEquals(SIZE_CATEGORY, newTypeDTO.getSizeCategory());
        assertEquals(AVERAGE_LIFESPAN, newTypeDTO.getAverageLifespan());
        assertEquals(CARE_LEVEL, newTypeDTO.getCareLevel());
    }

    @Test
    public void testUpdateType() {
        String TYPE_NAME = "Turtle";
        String DESCRIPTION = "Slow-moving reptile";
        Boolean ACTIVE = true;
        String SIZE_CATEGORY = "Small";
        Integer AVERAGE_LIFESPAN = 30;
        String CARE_LEVEL = "Easy";

        String UP_TYPE_NAME = "Turtle Updated";
        String UP_DESCRIPTION = "Ninja Turtle Updated";
        Boolean UP_ACTIVE = true;
        String UP_SIZE_CATEGORY = "Medium";
        Integer UP_AVERAGE_LIFESPAN = 35;
        String UP_CARE_LEVEL = "Medium";

        // Create initial type
        TypeDTO typeDTO = TypeDTO.builder()
                .name(TYPE_NAME)
                .description(DESCRIPTION)
                .active(ACTIVE)
                .sizeCategory(SIZE_CATEGORY)
                .averageLifespan(AVERAGE_LIFESPAN)
                .careLevel(CARE_LEVEL)
                .build();

        log.info(">" + typeDTO);
        TypeDTO typeDTOCreated = this.typeService.create(typeDTO);
        log.info(">>" + typeDTOCreated);

        // Update the type
        typeDTOCreated.setName(UP_TYPE_NAME);
        typeDTOCreated.setDescription(UP_DESCRIPTION);
        typeDTOCreated.setActive(UP_ACTIVE);
        typeDTOCreated.setSizeCategory(UP_SIZE_CATEGORY);
        typeDTOCreated.setAverageLifespan(UP_AVERAGE_LIFESPAN);
        typeDTOCreated.setCareLevel(UP_CARE_LEVEL);

        // Execute update
        TypeDTO upgradeTypeDTO = this.typeService.update(typeDTOCreated);
        log.info(">>>>" + upgradeTypeDTO);

        // Assertions
        assertEquals(UP_TYPE_NAME, upgradeTypeDTO.getName());
        assertEquals(UP_DESCRIPTION, upgradeTypeDTO.getDescription());
        assertEquals(UP_ACTIVE, upgradeTypeDTO.getActive());
        assertEquals(UP_SIZE_CATEGORY, upgradeTypeDTO.getSizeCategory());
        assertEquals(UP_AVERAGE_LIFESPAN, upgradeTypeDTO.getAverageLifespan());
        assertEquals(UP_CARE_LEVEL, upgradeTypeDTO.getCareLevel());
    }

    /**
     * Test for deleting a type
     */
    @Test
    public void testDeleteType() {
        String TYPE_NAME = "Spider";
        String DESCRIPTION = "Small rodent pet";
        Boolean ACTIVE = true;
        String SIZE_CATEGORY = "Small";
        Integer AVERAGE_LIFESPAN = 6;
        String CARE_LEVEL = "Hard";

        // Create type
        TypeDTO typeDTO = TypeDTO.builder()
                .name(TYPE_NAME)
                .description(DESCRIPTION)
                .active(ACTIVE)
                .sizeCategory(SIZE_CATEGORY)
                .averageLifespan(AVERAGE_LIFESPAN)
                .careLevel(CARE_LEVEL)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);
        log.info("Type created for deletion: " + newTypeDTO);

        // Delete the type
        try {
            this.typeService.delete(newTypeDTO.getId());
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }

        // Validation - should not find the deleted type
        try {
            this.typeService.findById(newTypeDTO.getId());
            fail("Type should have been deleted");
        } catch (TypeNotFoundException e) {
            assertTrue(true);
        }
    }
}
