package com.bini.Lucky_Round.repository;


import com.bini.Lucky_Round.entity.RewardStatus;
import com.bini.Lucky_Round.entity.SpinTheWheelFirstRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpinTheWheelFirstRegistrationRepo extends JpaRepository<SpinTheWheelFirstRegistration, Long> {

    @Query("select f from SpinTheWheelFirstRegistration f where f.msisdn = :identifier and f.status = :rewardStatus")
    List<SpinTheWheelFirstRegistration> findByMsisdnAndStatus(String identifier, RewardStatus rewardStatus);
}
