package com.charge.chargeManeger.api.dto;

import com.charge.chargeManeger.api.dto.enums.Enums;

import java.math.BigDecimal;

public record CobrancaDTO(Long id, String name, BigDecimal value, Enums.BillingType billingType , Enums.ChargeType chargeType, Long clienteId){
}
