package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.AllocationPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllocationPercentageRepo extends JpaRepository<AllocationPercentage, String> {

    Optional<AllocationPercentage> findByReasonTypeId(String reasonTypeId);
}
