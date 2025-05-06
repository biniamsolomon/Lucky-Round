package com.bini.Lucky_Round.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="tbl_spin_the_wheel_reward_list")
public class RewardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "amount")
    private int amount;

    @Column(name = "occurrences")
    private int occurrences;
}
