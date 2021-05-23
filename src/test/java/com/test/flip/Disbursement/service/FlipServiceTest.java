package com.test.flip.Disbursement.service;

import com.test.flip.Disbursement.dto.DisbursementDto;
import com.test.flip.Disbursement.entity.Disbursement;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.service.entity.DisbursementEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigInteger;
import java.util.Base64;

@ExtendWith(MockitoExtension.class)
public class FlipServiceTest {
    @InjectMocks
    FlipService flipService;

    @Mock
    PrepareDataService prepareDataService;

    @Mock
    HitToServerService hitToServerService;

    @Mock
    DisbursementEntityService disbursementEntityService;

    @Test
    void doDisbursement_succees(){

        ReflectionTestUtils.setField(flipService, "baseUrl", "baseUrl");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String encodedSecretKey = Base64.getEncoder().encodeToString("secretKey".getBytes());
        headers.set("Authorization", "basic " + encodedSecretKey);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("bank_code", "bankCode");
        map.add("account_number", "accountNumber");
        map.add("amount", "amount");
        map.add("remark", "remark");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        DisbursementDto disbursement = new DisbursementDto("bankCode", "accountNumber", 10000,"remark");

        Mockito.when(prepareDataService.setEntityForDisbursement(disbursement)).thenReturn(entity);
        Mockito.when(hitToServerService.send(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new ResponseEntity("{\n" +
                "    \"id\": 5535152564,\n" +
                "    \"amount\": 10000,\n" +
                "    \"status\": \"PENDING\",\n" +
                "    \"timestamp\": \"2019-05-21 09:12:42\",\n" +
                "    \"bank_code\": \"bni\",\n" +
                "    \"account_number\": \"1234567890\",\n" +
                "    \"beneficiary_name\": \"PT FLIP\",\n" +
                "    \"remark\": \"sample remark\",\n" +
                "    \"receipt\": null,\n" +
                "    \"time_served\": \"0000-00-00 00:00:00\",\n" +
                "    \"fee\": 4000\n" +
                "}", HttpStatus.OK));
        Mockito.doNothing().when(disbursementEntityService).storeData(Mockito.any());

        ResponseDisbursementModel responseDisbursementModel = flipService.doDisbursement(disbursement);
        Assertions.assertEquals(responseDisbursementModel.getStatus(),"PENDING" );
    }

    @Test
    void doCheckStatus_succees(){

        Disbursement disbursement = new Disbursement();
        disbursement.setId(new Long(1));
        disbursement.setTransactionId(new BigInteger("1233333"));
        Mockito.when(disbursementEntityService.findDisbursementByTransactionId(Mockito.anyString())).thenReturn(disbursement);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String encodedSecretKey = Base64.getEncoder().encodeToString("secretKey".getBytes());
        headers.set("Authorization", "basic " + encodedSecretKey);
        HttpEntity entity = new HttpEntity(headers);

        Mockito.when(prepareDataService.setEntityForCheckDisbursement()).thenReturn(entity);
        Mockito.when(hitToServerService.send(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new ResponseEntity("{\n" +
                "    \"id\": 5535152564,\n" +
                "    \"amount\": 10000,\n" +
                "    \"status\": \"SUCCESS\",\n" +
                "    \"timestamp\": \"2019-05-21 09:12:42\",\n" +
                "    \"bank_code\": \"bni\",\n" +
                "    \"account_number\": \"1234567890\",\n" +
                "    \"beneficiary_name\": \"PT FLIP\",\n" +
                "    \"remark\": \"sample remark\",\n" +
                "    \"receipt\": \"https://flip-receipt.oss-ap-southeast-5.aliyuncs.com/debit_receipt/126316_3d07f9fef9612c7275b3c36f7e1e5762.jpg\",\n" +
                "    \"time_served\": \"2019-05-21 09:26:11\",\n" +
                "    \"fee\": 4000\n" +
                "}", HttpStatus.OK));

        Mockito.doNothing().when(disbursementEntityService).updateDataDisbursementCheck(Mockito.any(), Mockito.any());
        ResponseDisbursementModel responseDisbursementModel = flipService.doCheckStatus("test1");
        Assertions.assertEquals(responseDisbursementModel.getStatus(),"SUCCESS" );
    }

}
