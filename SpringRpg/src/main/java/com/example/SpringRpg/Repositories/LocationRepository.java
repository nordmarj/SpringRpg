package com.example.SpringRpg.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringRpg.Entities.Location;

// This will be AUTO IMPLEMENTED by Spring into a Bean called locationRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByName(String name);
}