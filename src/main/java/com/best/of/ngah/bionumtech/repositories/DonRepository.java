package com.best.of.ngah.bionumtech.repositories;

import com.best.of.ngah.bionumtech.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonRepository extends JpaRepository<Don, Long> {
}
