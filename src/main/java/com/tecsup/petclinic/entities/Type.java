package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "types")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    @Column(name = "size_category", length = 20)
    private String sizeCategory;

    @Column(name = "average_lifespan")
    private Integer averageLifespan;

    @Column(name = "care_level", length = 20)
    private String careLevel;

    public Type(String name, String description, Boolean active, String sizeCategory,
                Integer averageLifespan, String careLevel) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.sizeCategory = sizeCategory;
        this.averageLifespan = averageLifespan;
        this.careLevel = careLevel;
    }
}
