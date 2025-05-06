package com.bini.Lucky_Round.service.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.cps.synccpsinterface.result.Result;
import com.mpesa.spin_the_wheel.entity.RewardStatus;
import com.mpesa.spin_the_wheel.entity.SpinTheWheelFailed;
import com.mpesa.spin_the_wheel.entity.SpinTheWheelSuccess;
import com.mpesa.spin_the_wheel.repository.SpinTheWheelFailedRepo;
import com.mpesa.spin_the_wheel.repository.SpinTheWheelSuccessRepo;
import com.mpesa.spin_the_wheel.service.SMSService;
import com.mpesa.spin_the_wheel.utils.G2SyncRequestService;
import com.mpesautils.logmanager.Log;
import com.mpesautils.logmanager.LogManager;
import com.mpesautils.logmanager.LogStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RetryFailed {

    private final SpinTheWheelFailedRepo failedRepo;
    private final SpinTheWheelSuccessRepo successRepo;
    private final G2SyncRequestService g2SyncRequestService;
    private final SMSService smsService;

    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public void retryFailed() {

        LogManager logManager = new LogManager(RetryFailed.class, UUID.randomUUID().toString(), "Process Retry");

        List<SpinTheWheelFailed> spinTheWheelFailed = failedRepo.findAllByStatus(RewardStatus.FAILED);

        logManager.info(new Log("Retry Failed Request", "Consumed failed Transactions for processing", LogStatus.PROCESSING, "",
                " ", " ", "", "", spinTheWheelFailed.toString()));

        if (spinTheWheelFailed.isEmpty()) {

            logManager.info(new Log("Retry Failed Request", "There is no failed Request in table", LogStatus.FINISHED, "",
                    " ", " ", " ", " ", ""));
            return;
        } else {

            try {

                for (SpinTheWheelFailed failedRequests : spinTheWheelFailed) {

                    boolean check = processFailedRequests(failedRequests, logManager);

                    if (!check) {
                        failedRequests.setTryCount(failedRequests.getTryCount() + 1);
                    }
                }
            } catch (Exception exception) {

                Log error = new Log("Retry Failed Request", "Error occurred where process the retry req:  " + exception, LogStatus.FINISHED, "",
                        " ", " ", "SVC0001", " ", "");
                logManager.error(error);
            }
        }
    }

    private boolean processFailedRequests(SpinTheWheelFailed failedReqs, LogManager logManager)
            throws NoSuchAlgorithmException, JsonProcessingException, KeyManagementException {

        int rewardAmount = Integer.parseInt(failedReqs.getRewardAmount());

        String sessionId = failedReqs.getSessionId();
        Result result = null;

        if (sessionId != null  && !sessionId.trim().isEmpty() && !successRepo.existsBySessionId(sessionId)) {

            try {

                result = g2SyncRequestService.sendB2CPaymentG2Request(failedReqs.getMsisdn(), rewardAmount, failedReqs.getRequestRefID());

            } catch (Exception e) {

                logManager.error(new Log("Process G2", "An exception occurred while processing the request", LogStatus.FINISHED,
                        "", "", "", "", e.getMessage(), e.getLocalizedMessage()));

                failedReqs.setMpesaResultDesc("An exception occurred while processing the request");
                failedReqs.setReason(e.getMessage());
                failedReqs.setTryCount(failedReqs.getTryCount() + 1);
                failedRepo.save(failedReqs);

                return false;
            }

            logManager.info(new Log("Process === G2", "Received response from M-PESA for B2C", LogStatus.PROCESSING, "",
                    "", "", result.getBody().getResultCode(), result.getBody().getResultDesc(), result.toString()));

            if (result.getBody() == null || result.getHeader() == null) {

                logManager.warn(new Log("PROCESS === FINISHED", "Received null object upon invoking M-PESA", LogStatus.FINISHED, failedReqs.getMsisdn(), " ", " ",
                        result.getBody().getResultCode(), result.getBody().getResultDesc(), result.toString()));

                failedReqs.setMpesaResultDesc("Received null object upon invoking M-PESA");
                failedReqs.setTryCount(failedReqs.getTryCount() + 1);
                failedRepo.save(failedReqs);

                return false;

            } else if (!result.getBody().getResultCode().equalsIgnoreCase("0")) {

                logManager.error(new Log("PROCESS === FINISHED", "Mpesa responded with failure for the B2C", LogStatus.FINISHED, failedReqs.getMsisdn(), " ", " ",
                        result.getBody().getResultCode(), result.getBody().getResultDesc(), result.toString()));

                failedReqs.setMpesaResultCode(result.getBody().getResultCode());
                failedReqs.setMpesaResultDesc(result.getBody().getResultDesc());
                failedReqs.setTryCount(failedReqs.getTryCount() + 1);
                failedRepo.save(failedReqs);

                return false;
            } else {

                logManager.info(new Log("Process Reward", "Success", LogStatus.PROCESSING, failedReqs.getMsisdn(),
                        " ", " ", result.getBody().getResultCode(), result.getBody().getResultDesc(), result.toString()));

                try {

                    failedReqs.setStatus(RewardStatus.SUCCESS);
                    failedReqs.setTryCount(failedReqs.getTryCount() + 1);
                    failedRepo.save(failedReqs);

                    logManager.info(new Log("Process Reward", "Successfully changed status in failed table", LogStatus.PROCESSING, failedReqs.getMsisdn(),
                            " ", " ", " ", "", ""));

                } catch (Exception e) {

                    logManager.error(new Log("process Req", "An exception occurred when trying to change status in failed table",
                            LogStatus.FINISHED, "", "", "", "", e.getMessage(), e.getLocalizedMessage()));
                }

                try {

                    saveSuccess(failedReqs, result);

                    logManager.info(new Log("Process Reward", "Successfully saved to Success Table", LogStatus.PROCESSING, failedReqs.getMsisdn(), " ",
                            " ", "", "", ""));

                } catch (Exception ex) {
                    logManager.info(new Log("Process Reward", "Exception occurred when saving in to success database ", LogStatus.FINISHED, failedReqs.getMsisdn(),
                            " ", " ", " ", ex.getMessage(), ex.getLocalizedMessage()));
                }

                try {
                    smsService.sendSMSWithLangCode(failedReqs.getMsisdn(), failedReqs.getRewardAmount());
                    logManager.info(new Log("SMS Service", "Successfully sent SMS", LogStatus.FINISHED, failedReqs.getMsisdn(), " ", " ", "", "", ""));
                } catch (Exception e) {
                    logManager.warn(new Log("SMS Service", "Failed to send SMS", LogStatus.FINISHED, failedReqs.getMsisdn(), " ", " ", "", e.getLocalizedMessage(), e.getMessage()));
                }
            }
        }  else {

            logManager.info(new Log("Process Failed", "Session id null or exists in success table", LogStatus.FINISHED, failedReqs.getMsisdn(), " ", " ", "", "", ""));
        }
        return true;
    }

    private void saveSuccess(SpinTheWheelFailed failedReqs, Result result) {

        SpinTheWheelSuccess spinTheWheelSuccess = new SpinTheWheelSuccess();

        spinTheWheelSuccess.setRequestRefID(failedReqs.getRequestRefID());
        spinTheWheelSuccess.setTransactionId(failedReqs.getTransactionId());
        spinTheWheelSuccess.setReasonTypeId(failedReqs.getReasonTypeId());
        spinTheWheelSuccess.setMsisdn(failedReqs.getMsisdn());
        spinTheWheelSuccess.setActualAmount(failedReqs.getActualAmount());
        spinTheWheelSuccess.setIdentifierType(failedReqs.getIdentifierType());
        spinTheWheelSuccess.setSecretKey(failedReqs.getSecretKey());
        spinTheWheelSuccess.setStatus(RewardStatus.CLAIMED);
        spinTheWheelSuccess.setRewardAmount(failedReqs.getRewardAmount());
        spinTheWheelSuccess.setSessionId(failedReqs.getSessionId());
        spinTheWheelSuccess.setMpesaResultCode(result.getBody().getResultCode());
        spinTheWheelSuccess.setMpesaResultDesc(result.getBody().getResultDesc());
        spinTheWheelSuccess.setRewardTransactionId(result.getHeader().getChannelSessionID());
        spinTheWheelSuccess.setTransactionDate(LocalDateTime.now());
        spinTheWheelSuccess.setReason("Reward successfully processed");
        successRepo.save(spinTheWheelSuccess);
    }
}
