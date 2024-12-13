package com.example.SpringRpg.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringRpg.Entities.Location;
import com.example.SpringRpg.Entities.LocationLink;

// This will be AUTO IMPLEMENTED by Spring into a Bean called actorRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationLinkRepository extends CrudRepository<LocationLink, Long> {
    List<LocationLink> findAllBySourceLocation(Location sourceLocation);
    List<LocationLink> findAllByDestinationLocation(Location destinationLocation);
}