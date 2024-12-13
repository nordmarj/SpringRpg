package com.example.SpringRpg.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringRpg.Entities.Actor;

// This will be AUTO IMPLEMENTED by Spring into a Bean called actorRepository
// CRUD refers Create, Read, Update, Delete

public interface ActorRepository extends CrudRepository<Actor, Long> {
    Actor findByName(String name);
}