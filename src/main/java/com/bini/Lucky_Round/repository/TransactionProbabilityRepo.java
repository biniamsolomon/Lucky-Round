package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.TransactionProbability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionProbabilityRepo extends JpaRepository<TransactionProbability, String> {

    Optional<TransactionProbability> findByReasonTypeId(String reasonTypeId);
}
