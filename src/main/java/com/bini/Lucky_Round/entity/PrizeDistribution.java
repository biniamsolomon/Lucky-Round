package com.bini.Lucky_Round.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="tbl_spin_the_wheel_prize_distribution")
public class PrizeDistribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;
    private double probability;
    private String prizeValue;
    // Getters & Setters
}
