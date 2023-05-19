package br.com.ibis.repositories;

import br.com.ibis.repositories.entities.HotelEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface HotelRepository extends MongoRepository<HotelEntity, String>, QueryByExampleExecutor<HotelEntity> {

    //List<HotelEntity> findAllByExample(Example<HotelEntity> example, Pageable pageable);

    @Query(value =
            "{'id': ?0, 'reservas': {$elemMatch: {'checkIn': {$lt: ?2}, 'checkOut': {$gt: ?1}}}}, {'reservas.$': 1}")
    Optional<HotelEntity> findCollidingReservaData(ObjectId id, LocalDate checkIn, LocalDate checkOut);
}
