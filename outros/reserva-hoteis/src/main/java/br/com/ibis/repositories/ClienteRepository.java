package br.com.ibis.repositories;

import br.com.ibis.repositories.entities.ClienteEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<ClienteEntity, ObjectId> {

    Optional<ClienteEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
