package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTypeRepository extends JpaRepository<Type, Long> {
}
