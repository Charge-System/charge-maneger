package com.charge.chargeManeger.api.dto;

import com.charge.chargeManeger.api.dto.enums.StatusCobranca;
import com.charge.chargeManeger.api.dto.enums.TipoCobranca;

import java.util.Date;

public record CobrancaDTO
        (Long id,
         Double valor,
         Date dataVencimento,
         TipoCobranca tipoCobranca,
         StatusCobranca statusCobranca,
         String idCobrancaAsaas,
         String idClienteAsaas){
}
