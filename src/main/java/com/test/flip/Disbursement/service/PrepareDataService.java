package com.test.flip.Disbursement.service;

import com.test.flip.Disbursement.dto.DisbursementDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;

@Service
public class PrepareDataService {

    @Value("${secret.key.client:xxx}")
    public String secretKey;


    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        headers.set("Authorization", "basic " + encodedSecretKey);
        return headers;
    }


    public HttpEntity setEntityForDisbursement(DisbursementDto disbursementDto) {
        HttpHeaders headers = createHeader();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("bank_code", disbursementDto.getBankCode());
        map.add("account_number", disbursementDto.getAccountNumber());
        map.add("amount", disbursementDto.getAmount() + "");
        map.add("remark", disbursementDto.getRemark());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return entity;

    }

    public HttpEntity setEntityForCheckDisbursement(){
        HttpHeaders headers = createHeader();
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }

}
