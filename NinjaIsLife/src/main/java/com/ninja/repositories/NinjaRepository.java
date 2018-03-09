package com.ninja.repositories;

import com.ninja.models.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NinjaRepository extends JpaRepository<Ninja, Long> {
}
