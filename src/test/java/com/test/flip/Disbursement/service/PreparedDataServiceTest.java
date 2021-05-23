package com.test.flip.Disbursement.service;

import com.test.flip.Disbursement.dto.DisbursementDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.MultiValueMap;

@ExtendWith(MockitoExtension.class)
public class PreparedDataServiceTest {
    @InjectMocks
    PrepareDataService prepareDataService;

    @Test
    public void setEntityForCheckDisbursement_success() {
        ReflectionTestUtils.setField(prepareDataService, "secretKey", "secretKey");
        HttpEntity entity = prepareDataService.setEntityForCheckDisbursement();
        Assertions.assertEquals(MediaType.APPLICATION_FORM_URLENCODED, entity.getHeaders().getContentType());
    }

    @Test
    public void setEntityForDisbursement_success() {
        ReflectionTestUtils.setField(prepareDataService, "secretKey", "secretKey");
        DisbursementDto disbursementDto = new DisbursementDto("bankCode", "accountNumber", 10000, "sampleRemark");

        HttpEntity entity = prepareDataService.setEntityForDisbursement(disbursementDto);
        MultiValueMap<String, String> stringStringMultiValueMap = (MultiValueMap<String, String>) entity.getBody();
        Assertions.assertEquals("bankCode", stringStringMultiValueMap.get("bank_code").get(0));
    }
}
