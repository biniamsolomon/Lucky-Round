package com.bini.Lucky_Round.service;

import com.bini.Lucky_Round.config.ConfigProperties;
import com.bini.Lucky_Round.dto.SMSResponse;
import com.bini.Lucky_Round.dto.sms.Receiver;
import com.bini.Lucky_Round.dto.sms.SMSRequest;
import com.bini.Lucky_Round.dto.sms.Sender;
import com.bini.Lucky_Round.utils.GlobalVariables;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class SMSService {

    private final WebClient smsClient;
    private final ConfigProperties configProperties;

    @Value("${app.sms-sender-name}")
    private String smsSenderName;

    @Async

    public SMSResponse sendSMSWithLangCode(String debitParty, String rewardAmount)
            throws NoSuchAlgorithmException, KeyManagementException {

        System.out.println("msisdn: " + debitParty + " amount: " + rewardAmount);

        String preferredLang = "en";


        String   smsToSend = GlobalVariables.TRANSLATION_MAP_BIRR_CHECK_BALANCE.getOrDefault(preferredLang, preferredLang);

        return getSmsResponse(debitParty, rewardAmount, preferredLang, smsToSend);
    }

    private SMSResponse getSmsResponse(String debitParty, String rewardAmount, String preferredLang, String smsToSend) {

       String smsToSendAfter = smsToSend.
               replace("{rewardAmount}", rewardAmount);

        //smsToSend = String.format(smsToSend, rewardAmount, rewardAmount);

        System.out.println("THE LANGUAGE CODE IS: " + preferredLang);
        System.out.println("THE CONTENT OF SMS IS: " + smsToSendAfter);

        SMSResponse smsResponse = sendSMS(debitParty, smsToSendAfter);
        System.out.println("SMS Response: " + smsResponse.toString());

        return smsResponse;
    }

    public SMSResponse sendSMS(String msisdn, String smsContent) {
        Sender sender = new Sender();
        sender.setName(smsSenderName);
        Receiver receiver = new Receiver();
        receiver.setId(msisdn);
        List<Receiver> receivers = new ArrayList<>();
        receivers.add(receiver);

        SMSRequest smsRequest = SMSRequest.builder()
                .content(smsContent)
                .commandID("SendSMS")
                .requestRefID(String.valueOf(UUID.randomUUID()))
                .sender(sender)
                .receivers(receivers)
                .messageType("SMS")
                .build();

        return this.smsClient.post()
                .uri(this.configProperties.getSmsApiUrl())
                .headers(httpHeaders -> httpHeaders.setBasicAuth(this.configProperties.getSmsApiUsername(),
                        this.configProperties.getSmsApiPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(smsRequest)
                .retrieve()
                .bodyToMono(SMSResponse.class)
                .block();
    }
}
