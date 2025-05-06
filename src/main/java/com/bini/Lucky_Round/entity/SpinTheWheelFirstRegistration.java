package com.bini.Lucky_Round.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="tbl_spin_the_wheel_first_registration")
public class SpinTheWheelFirstRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "reason_type_id")
    private String reasonTypeId;

    @Column(name = "msisdn")
    private String msisdn;

    @Enumerated(EnumType.STRING)
    private RewardStatus status;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
