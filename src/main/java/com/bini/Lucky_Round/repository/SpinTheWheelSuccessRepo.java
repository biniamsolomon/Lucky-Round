package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.SpinTheWheelSuccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpinTheWheelSuccessRepo extends JpaRepository<SpinTheWheelSuccess, Long> {

    @Query("select r from SpinTheWheelSuccess r where r.msisdn=:msisdn and DATE(r.transactionDate) = DATE(:localDateTime)")
    List<SpinTheWheelSuccess> findByMsisdnAndTransactionDate(String msisdn, LocalDateTime localDateTime);

    @Query("select count(r.msisdn) from SpinTheWheelSuccess r where r.msisdn=:msisdn and DATE(r.transactionDate) = DATE(:localDateTime)")
    long countByMsisdnAndTransactionDate(String msisdn, LocalDateTime localDateTime);


//    @Query("SELECT COALESCE(SUM(CAST(s.rewardAmount AS double)), 0) FROM SpinTheWheelSuccess s " +
//            "WHERE s.reasonTypeId = :reasonTypeId AND s.transactionDate >= :oneHourAgo")
//    double getTotalRewardsForLastOneHour(@Param("reasonTypeId") String reasonTypeId,
//                                         @Param("oneHourAgo") LocalDateTime oneHourAgo);


    @Query("SELECT COALESCE(SUM(CAST(s.rewardAmount AS double)), 0) " +
            "FROM SpinTheWheelSuccess s " +
            "WHERE s.reasonTypeId = :reasonTypeId " +
            "AND s.transactionDate >= :startOfHour " +
            "AND s.transactionDate < :startOfHourPlusOne")
    double getTotalRewardsForHour(@Param("reasonTypeId") String reasonTypeId,
                                  @Param("startOfHour") LocalDateTime startOfHour,
                                  @Param("startOfHourPlusOne") LocalDateTime startOfHourPlusOne);


    @Query("SELECT COALESCE(SUM(CAST(s.rewardAmount AS double)), 0) " +
            "FROM SpinTheWheelSuccess s " +
            "WHERE s.transactionDate >= :startOfDay " +
            "AND s.transactionDate < :endOfDay")
    Double getTotalRewardsForDays(@Param("startOfDay") LocalDateTime startOfDay,
                                  @Param("endOfDay") LocalDateTime endOfDay);

    SpinTheWheelSuccess findBySessionId(String sessionId);


    boolean existsBySessionId(String sessionId);

}
