package com.allan_dev.instrutorFacil.repository;

import com.allan_dev.instrutorFacil.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    boolean existsByNameIgnoreCase (String name);
}
