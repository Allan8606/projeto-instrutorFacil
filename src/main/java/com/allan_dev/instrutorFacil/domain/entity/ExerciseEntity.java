package com.allan_dev.instrutorFacil.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(
            name = "name",
            nullable = false,
            length = 150
    )
    private String name;

    @Column(
            name = "description",
            length = 150
    )
    private String description;

    @Column(
            name = "link"
    )
    private String link;


    @ManyToMany(mappedBy = "exercises")
    private List<TrainingEntity> trainingExercises = new ArrayList<>();
}
