package com.voteghost.repositories;

import com.voteghost.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
        Optional<Feature> findById(Long id);
}
