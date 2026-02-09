package com.allan_dev.instrutorFacil.domain.repository;

import com.allan_dev.instrutorFacil.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLogin(String login);
}
