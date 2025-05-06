package com.bini.Lucky_Round.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_spin_the_wheel_reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reward_name", nullable = false)
    private String rewardName;

    @Column(name = "reward_value", nullable = false)
    private String rewardValue;

    @Column(name = "reward_description")
    private String rewardDescription;

    @ManyToOne
    @JoinColumn(name = "reward_type_id", referencedColumnName = "id", nullable = false)
    private RewardType rewardType;

    @OneToOne(optional = true)
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id", nullable = true)
    private TransactionType transactionType;
}
