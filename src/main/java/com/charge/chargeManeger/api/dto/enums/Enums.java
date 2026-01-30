package com.charge.chargeManeger.api.dto.enums;

public class Enums {
    public enum BillingType {
        UNDEFINED, BOLETO, CREDIT_CARD, PIX
    }

    // chargeType
    public enum ChargeType {
        DETACHED, RECURRENT, INSTALLMENT
    }
}
