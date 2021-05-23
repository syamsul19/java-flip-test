package com.test.flip.Disbursement.service;

import com.test.flip.Disbursement.entity.Disbursement;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.repository.DisbursementRepository;
import com.test.flip.Disbursement.service.entity.DisbursementEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

@ExtendWith(MockitoExtension.class)
public class DisbursementEntityServiceTest {
    @InjectMocks
    DisbursementEntityService disbursementEntityService;

    @Mock
    DisbursementRepository disbursementRepository;

    @Test
    void storeData_success(){
        ResponseDisbursementModel responseDisbursementModel = new ResponseDisbursementModel();
        responseDisbursementModel.setAccountNumber("1234567890");
        responseDisbursementModel.setAmount(10000);
        responseDisbursementModel.setBankCode("bni");
        responseDisbursementModel.setBeneficiaryName("PT FLIP");
        responseDisbursementModel.setFee(4000);
        responseDisbursementModel.setReceipt(null);
        responseDisbursementModel.setRemark("sample remark");
        responseDisbursementModel.setStatus("PENDING");
        responseDisbursementModel.setTimeServed("0000-00-00 00:00:00");
        responseDisbursementModel.setTimestamp("2019-05-21 09:12:42");
        responseDisbursementModel.setTransactionId(new BigInteger("5535152564"));

        Disbursement disbursement = Mockito.mock(Disbursement.class);
        Mockito.when(disbursementRepository.save(Mockito.any())).thenReturn(disbursement);
        disbursementEntityService.storeData(responseDisbursementModel);
    }


    @Test
    void findDisbursementByTransactionId_success(){
        Disbursement disbursement = new Disbursement();
        disbursement.setId(new Long(1));
        disbursement.setTransactionId(new BigInteger("123333"));
        Mockito.when(disbursementRepository.findByTransactionId(Mockito.any())).thenReturn(disbursement);

        Disbursement disbursementResult = disbursementEntityService.findDisbursementByTransactionId("123333");
        Assertions.assertEquals(disbursementResult.getTransactionId(), disbursement.getTransactionId());

    }

    @Test
    void updateDataDisbursementCheck_success(){
        Disbursement disbursement = new Disbursement();
        disbursement.setId(new Long(1));
        disbursement.setTransactionId(new BigInteger("123333"));

        ResponseDisbursementModel responseDisbursementModel = new ResponseDisbursementModel();
        responseDisbursementModel.setStatus("SUCCESS");
        responseDisbursementModel.setTimeServed("2019-05-21 09:26:11");
        responseDisbursementModel.setReceipt("link-receipt");


        Mockito.when(disbursementRepository.save(Mockito.any())).thenReturn(disbursement);
        disbursementEntityService.updateDataDisbursementCheck(disbursement,responseDisbursementModel);

        Assertions.assertEquals(disbursement.getStatus(), responseDisbursementModel.getStatus());
    }
}
