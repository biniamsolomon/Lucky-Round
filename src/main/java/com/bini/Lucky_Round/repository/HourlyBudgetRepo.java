package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.HourlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HourlyBudgetRepo extends JpaRepository<HourlyBudget, Long> {

    Optional<HourlyBudget> findTopByOrderByTidAsc();
}
