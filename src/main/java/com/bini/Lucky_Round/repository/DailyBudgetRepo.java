package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.DailyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyBudgetRepo extends JpaRepository<DailyBudget, Long> {
    Optional<DailyBudget> findTopByOrderByTidAsc();
}
