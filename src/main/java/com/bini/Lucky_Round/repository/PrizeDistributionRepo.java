package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.PrizeDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeDistributionRepo extends JpaRepository<PrizeDistribution, Double> {
    List<PrizeDistribution> findAllByOrderByProbabilityDesc();
}