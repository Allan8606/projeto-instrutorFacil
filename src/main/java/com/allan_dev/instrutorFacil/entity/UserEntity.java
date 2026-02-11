package com.allan_dev.instrutorFacil.entity;


import com.allan_dev.instrutorFacil.entity.enuns.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
