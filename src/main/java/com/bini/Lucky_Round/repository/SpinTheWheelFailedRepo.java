package com.bini.Lucky_Round.repository;


import com.bini.Lucky_Round.entity.RewardStatus;
import com.bini.Lucky_Round.entity.SpinTheWheelFailed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpinTheWheelFailedRepo extends JpaRepository<SpinTheWheelFailed, Long> {

    @Query("select f from SpinTheWheelFailed f where f.status = :rewardStatus and f.tryCount <=10")
    List<SpinTheWheelFailed> findAllByStatus(RewardStatus rewardStatus);
}
