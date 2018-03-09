package com.ninja.repositories;

import com.ninja.models.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClanRepository extends JpaRepository<Clan, Long> {
}
