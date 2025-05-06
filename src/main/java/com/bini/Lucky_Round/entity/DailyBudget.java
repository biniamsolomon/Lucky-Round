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
@Table(name ="tbl_spin_the_wheel_daily_budget")
public class DailyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;
    @Column(name = "daily_budget")
    private double dailyBudget;
    @Column(name = "daily_budget_eth")
    private double dailyBudgetETH;


}
