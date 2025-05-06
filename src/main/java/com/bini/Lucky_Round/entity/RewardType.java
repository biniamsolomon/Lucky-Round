package com.bini.Lucky_Round.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_spin_the_wheel_reward_type")
public class RewardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    @Entity
    @Table(name = "reward_tracking")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

    public static class RewardTracking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String reasonTypeId;
        private String msisdn;
        private double rewardAmount;
        private LocalDateTime rewardTimestamp;
    }
}
