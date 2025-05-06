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
@Table(name ="tbl_spin_the_wheel_allocation_percentage")
public class AllocationPercentage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "reason_type_id")
    private String reasonTypeId;
    @Column(name = "percentage")
    private double percentage;
    @Column(name = "percentage_eth")
    private double percentageETH;
    // Getters & Setters
}
