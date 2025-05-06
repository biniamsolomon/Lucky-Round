package com.bini.Lucky_Round.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
//@RedisHash(value = "SpinRewardRedisModel", timeToLive = 604800)  // 1 week
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spin_the_wheel_elligible_rewards")
public class SpinTheWheelUnclaimed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId;
    private String amount;
    private String transactionId;
    private String reasonTypeId;
    private String rewardAmount;
    private String rewardType;
    private String transactionType;
    private String receiverMsisdn;
    private Integer remainingChance;
    @Enumerated(EnumType.STRING)
    private RewardStatus status;
    private LocalDateTime transactionDate;
    private String identifierType;
    private String identifier;
    private String shortCode;
    private String securityCredential;
    private String secretKey;

}
