package com.bini.Lucky_Round.service;

import com.bini.Lucky_Round.dto.SpinB2CRequest;
import com.bini.Lucky_Round.entity.RewardStatus;
import com.bini.Lucky_Round.entity.SpinTheWheelFailed;
import com.bini.Lucky_Round.entity.SpinTheWheelSuccess;
import com.bini.Lucky_Round.entity.SpinTheWheelUnclaimed;
import com.bini.Lucky_Round.repository.SpinTheWheelFailedRepo;
import com.bini.Lucky_Round.repository.SpinTheWheelSuccessRepo;
import com.bini.Lucky_Round.repository.SpinTheWheelUnclaimedRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ProcessB2C {

 private final SpinTheWheelSuccessRepo spinTheWheelSuccessRepo;
 private final SMSService smsService;
 private final SpinTheWheelFailedRepo spinTheWheelFailedRepo;
 private final SpinTheWheelUnclaimedRepo unclaimedRepo;

    // * todo -find by id and status
    // * todo -if found change status *//

    public void processB2CReward(SpinB2CRequest spinRequest, Logger logManager) throws NoSuchAlgorithmException, KeyManagementException {

        Optional<SpinTheWheelUnclaimed> spinTheWheelUnclaimed = unclaimedRepo.findBySessionIdAndStatus(spinRequest.getChannelSessionId(), RewardStatus.UNCLAIMED);

        logManager.info("The record is found in Db" + spinTheWheelUnclaimed);

        if (spinTheWheelUnclaimed.isEmpty()) {
            logManager.info("No reward found for session ID" + spinRequest.getChannelSessionId());
            return;
        }

        String msisdn = spinTheWheelUnclaimed.get().getReceiverMsisdn();

        String decryptedMpesaPIN;

            SpinTheWheelUnclaimed spinTheWheelUnclaimed1 = spinTheWheelUnclaimed.get();

            SpinTheWheelFailed spinTheWheelFailed = new SpinTheWheelFailed();
            logManager.info("The Prize Request is received to reward B2C");

            String identifier = spinTheWheelUnclaimed1.getReceiverMsisdn();
            String reward = spinTheWheelUnclaimed1.getRewardAmount();

            logManager.info("sent B2c request to G2");

            saveSuccessAndSendSMS(spinRequest, logManager, spinTheWheelUnclaimed1);

            spinTheWheelUnclaimed1.setStatus(RewardStatus.CLAIMED);
            unclaimedRepo.save(spinTheWheelUnclaimed1);
            logManager.info("The reward is successfully processed");

        }

    private void saveFailedTransactions(SpinB2CRequest spinRequest, SpinTheWheelFailed spinTheWheelFailed,
                                        SpinTheWheelUnclaimed spinTheWheelUnclaimed1) {

        spinTheWheelFailed.setTransactionId(spinTheWheelUnclaimed1.getTransactionId());
        spinTheWheelFailed.setReasonTypeId(spinTheWheelUnclaimed1.getReasonTypeId());
        spinTheWheelFailed.setMsisdn(spinTheWheelUnclaimed1.getReceiverMsisdn());
        spinTheWheelFailed.setRewardAmount(spinTheWheelFailed.getRewardAmount());
        spinTheWheelFailed.setRequestRefID(spinRequest.getRequestRefID());
        spinTheWheelFailed.setTryCount(1);
        spinTheWheelFailed.setStatus(RewardStatus.FAILED);
        spinTheWheelFailed.setTransactionDate(LocalDateTime.now());
        spinTheWheelFailed.setReason("Failed to process reward");
        spinTheWheelFailed.setSessionId(spinTheWheelUnclaimed1.getSessionId());
        spinTheWheelFailedRepo.save(spinTheWheelFailed);
    }

    public void saveSuccessAndSendSMS(SpinB2CRequest spinRequest, Logger logManager, SpinTheWheelUnclaimed spinTheWheelUnclaimed1) {

        try {
            saveSuccess(spinTheWheelUnclaimed1, spinRequest);


        } catch (Exception ex) {

        }

        try {
            smsService.sendSMSWithLangCode(spinTheWheelUnclaimed1.getReceiverMsisdn(), spinTheWheelUnclaimed1.getRewardAmount());
        } catch (Exception e) {
            logManager.info("Failed to send SMS");
        }
    }

    private void saveSuccess(SpinTheWheelUnclaimed spinTheWheelUnclaimed1, SpinB2CRequest spinRequest) {
        SpinTheWheelSuccess spinTheWheelSuccess = new SpinTheWheelSuccess();

        spinTheWheelSuccess.setRequestRefID(spinRequest.getRequestRefID());
        spinTheWheelSuccess.setTransactionId(spinTheWheelUnclaimed1.getTransactionId());
        spinTheWheelSuccess.setReasonTypeId(spinTheWheelUnclaimed1.getReasonTypeId());
        spinTheWheelSuccess.setMsisdn(spinTheWheelUnclaimed1.getReceiverMsisdn());
        spinTheWheelSuccess.setTransactionType(spinTheWheelUnclaimed1.getTransactionType());
        spinTheWheelSuccess.setRewardAmount(spinTheWheelUnclaimed1.getRewardAmount());
        spinTheWheelSuccess.setSessionId(spinTheWheelUnclaimed1.getSessionId());
        spinTheWheelSuccess.setActualAmount(spinTheWheelUnclaimed1.getAmount());
        spinTheWheelSuccess.setIdentifierType(spinTheWheelUnclaimed1.getIdentifierType());
        spinTheWheelSuccess.setSecretKey(spinTheWheelUnclaimed1.getSecretKey());
        spinTheWheelSuccess.setStatus(RewardStatus.CLAIMED);
        spinTheWheelSuccess.setTransactionDate(LocalDateTime.now());
        spinTheWheelSuccess.setReason("Reward successfully processed");
        spinTheWheelSuccessRepo.save(spinTheWheelSuccess);
    }
    }

