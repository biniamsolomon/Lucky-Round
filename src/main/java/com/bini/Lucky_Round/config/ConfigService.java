package com.bini.Lucky_Round.config;

import com.bini.Lucky_Round.entity.*;
import com.bini.Lucky_Round.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
//@EnableCaching
public class ConfigService {

    private final AllocationPercentageRepo allocationPercentageRepo;
    private final TransactionProbabilityRepo transactionProbabilityRepo;
    private final PrizeDistributionRepo prizeDistributionRepo;
    private final HourlyBudgetRepo hourlyBudgetRepo;
    private final DailyBudgetRepo dailyBudgetRepo;

//    @Cacheable("hourlyBudget")
    public Double getHourlyBudget() {
        return Objects.requireNonNull(hourlyBudgetRepo).findTopByOrderByTidAsc()
                .map(HourlyBudget::getHourlyBudget)
                .orElseThrow(() -> new RuntimeException("Hourly budget not configured"));
    }

//    @Cacheable("hourlyBudgetETH")
    public Double getHourlyBudgetETH() {
        return Objects.requireNonNull(hourlyBudgetRepo).findTopByOrderByTidAsc()
                .map(HourlyBudget::getHourlyBudgetETH)
                .orElseThrow(() -> new RuntimeException("Hourly budget not configured"));
    }

//    @Cacheable("dailyBudget")
    public Double getDailyBudget() {
        return Objects.requireNonNull(dailyBudgetRepo).findTopByOrderByTidAsc()
                .map(DailyBudget::getDailyBudget)
                .orElseThrow(() -> new RuntimeException("Hourly budget not configured"));
    }

//    @Cacheable("dailyBudgetETH")
    public Double getDailyBudgetETH() {
        return Objects.requireNonNull(dailyBudgetRepo).findTopByOrderByTidAsc()
                .map(DailyBudget::getDailyBudgetETH)
                .orElseThrow(() -> new RuntimeException("Hourly budget not configured"));
    }

//    @Cacheable("allocationPercentage")
    public double getAllocationPercentage(String reasonTypeId) {
        return allocationPercentageRepo.findByReasonTypeId(reasonTypeId)
                .map(AllocationPercentage::getPercentage)
                .orElse(0.0);
    }

//    @Cacheable("allocationPercentageETH")
    public double getAllocationPercentageETH(String reasonTypeId) {
        return allocationPercentageRepo.findByReasonTypeId(reasonTypeId)
                .map(AllocationPercentage::getPercentageETH)
                .orElse(0.0);
    }

//    @Cacheable("transactionProbability")
    public double getTransactionProbability(String reasonTypeId) {
        return transactionProbabilityRepo.findByReasonTypeId(reasonTypeId)
                .map(TransactionProbability::getProbability)
                .orElse(0.0); // Default or throw exception
    }

//    @Cacheable("transactionProbabilityETH")
    public double getTransactionProbabilityETH(String reasonTypeId) {
        return transactionProbabilityRepo.findByReasonTypeId(reasonTypeId)
                .map(TransactionProbability::getProbabilityETH)
                .orElse(0.0); // Default or throw exception
    }

//    @Cacheable("prizeMap")
    public Map<Double, String> getPrizeMap() {
        return prizeDistributionRepo.findAllByOrderByProbabilityDesc()
                .stream()
                .collect(Collectors.toMap(
                        PrizeDistribution::getProbability,
                        PrizeDistribution::getPrizeValue,
                        (a, b) -> a, // in case of duplicate
                        LinkedHashMap::new // keep order
                ));
    }
}
