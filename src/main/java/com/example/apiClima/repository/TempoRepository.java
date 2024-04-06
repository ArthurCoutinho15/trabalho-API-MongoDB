package com.example.apiClima.repository;

import com.example.apiClima.model.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TempoRepository extends MongoRepository<Entity, String> {

}
