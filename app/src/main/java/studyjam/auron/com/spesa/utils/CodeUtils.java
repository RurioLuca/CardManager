package studyjam.auron.com.spesa.utils;

import movile.com.creditcardguide.model.IssuerCode;

/**
 * Created by luca on 4/22/16.
 */
public class CodeUtils {

    public static IssuerCode getTypeCard(String type) {
        if (type == null || type.equals("")) {
            return IssuerCode.PAYPAL;
        }
        switch (type) {
            case "VISACREDITO":
                return IssuerCode.VISACREDITO;
            case "AMEX":
                return IssuerCode.AMEX;
            case "AURA":
                return IssuerCode.AURA;
            case "DINERS":
                return IssuerCode.DINERS;
            case "PAYPAL":
                return IssuerCode.PAYPAL;
            case "ELO":
                return IssuerCode.ELO;
            case "HIPERCARD":
                return IssuerCode.HIPERCARD;
            case "MASTERCARD":
                return IssuerCode.MASTERCARD;
            case "NUBANK":
                return IssuerCode.NUBANK;
            case "VISAELECTRON":
                return IssuerCode.VISAELECTRON;
            case "OTHER":
                return IssuerCode.OTHER;
            default:
                return IssuerCode.VISACREDITO;
        }
    }


}
