package com.bini.Lucky_Round.utils;

import java.util.Map;

public final class GlobalVariables {

    private GlobalVariables(){
        throw new IllegalStateException("Utility Class");
    }

    public static final String ENGLISH = "en";

    public static final String AMHARIC = "amh_ET";

    public static final String OROMIFFA = "orm_ET";

    public static final String SOMALI = "som_ET";

    public static final String TIGRIGNA = "tir_ET";


    public static final Map<String, String> TRANSLATION_MAP_BIRR_CHECK_BALANCE = Map.ofEntries(

            Map.entry(ENGLISH, "Congratulations! You won {rewardAmount} birr by spinning the Wheel of Fortune. Use M-Birr to Send Money, Buy Airtime & Packages, and Make Payments."),
            Map.entry(AMHARIC, "Congratulations! You won {rewardAmount} birr by spinning the  Wheel of Fortune. Use M-Birr to Send Money, Buy Airtime & Packages, and Make Payments."),
            Map.entry(OROMIFFA, "Congratulations! You won {rewardAmount} birr by spinning the  Wheel of Fortune. Use M-Birr to Send Money, Buy Airtime & Packages, and Make Payments."),
            Map.entry(TIGRIGNA, "Congratulations! You won {rewardAmount} birr by spinning the  Wheel of Fortune. Use M-Birr to Send Money, Buy Airtime & Packages, and Make Payments."),
            Map.entry(SOMALI, "Congratulations! You won {rewardAmount} birr by spinning the  Wheel of Fortune. Use M-Birr to Send Money, Buy Airtime & Packages, and Make Payments."));

}


