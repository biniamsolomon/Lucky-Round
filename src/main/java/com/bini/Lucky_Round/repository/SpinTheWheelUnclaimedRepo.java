package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.RewardStatus;
import com.bini.Lucky_Round.entity.SpinTheWheelUnclaimed;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpinTheWheelUnclaimedRepo extends JpaRepository<SpinTheWheelUnclaimed,Long> {

    @Query("select f from SpinTheWheelUnclaimed f where f.sessionId = :channelSessionId and f.status = :status")
    Optional<SpinTheWheelUnclaimed> findBySessionIdAndStatus(String channelSessionId, RewardStatus status);

    @Modifying
    @Transactional
    @Query("delete from SpinTheWheelUnclaimed f where f.sessionId = :channelSessionId")
    void deleteBySessionId(@Param("channelSessionId") String channelSessionId);

}
