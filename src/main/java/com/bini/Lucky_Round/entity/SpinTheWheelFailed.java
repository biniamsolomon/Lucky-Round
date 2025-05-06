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
@Table(name ="tbl_spin_the_wheel_failed")
public class SpinTheWheelFailed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long tid;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;
    private String sessionId;


    @Column(name = "reason_type_id")
    private String reasonTypeId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "actual_amount")
    private String actualAmount;


    @Column(name = "RequestRefID")
    private String requestRefID;

    @Enumerated(EnumType.STRING)
    private RewardStatus status;

    @Column(name = "reward_amount")
    private String rewardAmount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "mpesa_result_code")
    private String mpesaResultCode;

    @Column(name = "mpesa_result_desc")
    private String mpesaResultDesc;

    @Column(name = "reward_transaction_id")
    private String rewardTransactionId;

    private String secretKey;
    private String securityCredential;
    private String identifierType;
    private int tryCount;

}
