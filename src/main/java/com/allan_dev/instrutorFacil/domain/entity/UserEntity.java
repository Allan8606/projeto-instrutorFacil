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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(
            name = "name",
            nullable = false,
            length = 150
    )
    private String name;

    @Column(
            name = "login",
            unique = true,
            nullable = false,
            length = 255
    )
    private String login;

    @Column(
            name = "password",
            nullable = false,
            length = 150
    )
    private String password;

    @OneToMany(
            mappedBy = "user", //Nome do atributo que está na entidade que faz relacção(Training)
            cascade = CascadeType.ALL, //Tudo que você fizer com User, o JPA faz com os Training
            orphanRemoval = true //Se um treino deixar de pertencer a um usuário, ele será deletado do banco
    )
    private List<TrainingEntity> trainingExercises = new ArrayList<>();

}
