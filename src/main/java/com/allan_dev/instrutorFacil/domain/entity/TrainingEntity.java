package com.allan_dev.instrutorFacil.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "training")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "training_exercise", //nome da tabela intermediaria
            joinColumns = @JoinColumn(name = "training_id"), //representa a FK do lado dono da relação.
            inverseJoinColumns = @JoinColumn(name = "exercise_id") //Representa a FK da OUTRA entidade (Exercise)
    )
    private List<ExerciseEntity> exercises = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(columnDefinition = "TEXT")
    private String observations;

}
