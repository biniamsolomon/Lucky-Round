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
@Table(name ="tbl_spin_the_wheel_transaction_probability")
public class TransactionProbability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;
    @Column(name = "reason_type_id")
    private String reasonTypeId;
    @Column(name = "probability")
    private double probability;
    @Column(name = "probability_eth")
    private double probabilityETH;

    // Getters & Setters
}
