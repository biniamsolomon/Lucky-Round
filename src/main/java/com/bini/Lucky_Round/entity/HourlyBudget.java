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
@Table(name ="tbl_spin_the_wheel_hourly_budget")
public class HourlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;
    @Column(name = "hourly_budget")
    private double hourlyBudget;
    @Column(name = "hourly_budget_eth")
    private double hourlyBudgetETH;


}
