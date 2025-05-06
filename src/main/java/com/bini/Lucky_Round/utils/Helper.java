package com.bini.Lucky_Round.utils;

import org.springframework.stereotype.Component;

@Component
public class Helper {

    public static String appendCountryCodeToMSISDN(String msisdn, String countryCode)
    {
        // if 9 characters, append country code
        if(msisdn.length() == 9)
            return countryCode + msisdn;

            // if its 10 characters in length, remove starting digit and append country code
            // i.e 0710733666 becomes 251710733666
        else if (msisdn.length() == 10)
            return countryCode + msisdn.substring(1);

            // If it's 12 in length, return as is
            // e.g. 251710733666 remains 251710733666
        else if(msisdn.length() == 12)
            return msisdn;

            // else length is 13
            // e.g +251710733666 becomes 251710733666
        else if (msisdn.length() == 13)
            return msisdn.substring(1);

            // if length is > 13 or < 9 or == 11, then it's an invalid msisdn so
            // return as is
        else
            return msisdn;
    }

    public static String maskMsisdn(String msisdn)
    {
        // if it's 710733666, return 7107***66
        if(msisdn.length() == 9)
            return msisdn.substring(0, 4) + "***" + msisdn.substring(7);

            // if length is 10 e.g. 0710733666, return 07107***66
        else if(msisdn.length() == 10)
            return msisdn.substring(0, 5) + "***" + msisdn.substring(8);

            // if length is 12 such as 251710733666, return 2517107***66
        else if (msisdn.length() == 12)
            return msisdn.substring(0, 7) + "***" + msisdn.substring(10);

        // if length is 13 such as +251710733666, return +2517107***66
        else if(msisdn.length() == 13)
            return msisdn.substring(0, 8) + "***" + msisdn.substring(11);

            // If the length is less than 9 or greater than 13 then the msisdn is invalid and
            // there is no need to mask
        else
            return msisdn;
    }
}
