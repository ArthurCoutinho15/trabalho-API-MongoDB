package com.example.apiFipe.repository;


import com.example.apiFipe.model.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FipeRepository extends MongoRepository<Entity, String> {

}