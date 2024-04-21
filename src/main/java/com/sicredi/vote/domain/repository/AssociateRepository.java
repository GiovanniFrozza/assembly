package com.sicredi.vote.domain.repository;

import com.sicredi.vote.domain.model.Associate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, String> {
    Optional<Associate> findByCpf(String cpf);

}
