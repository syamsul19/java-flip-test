package com.test.flip.Disbursement.service.entity;

import com.test.flip.Disbursement.entity.Disbursement;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.repository.DisbursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DisbursementEntityService {

    @Autowired
    DisbursementRepository disbursementRepository;

    public void storeData(ResponseDisbursementModel responseDisbursementModel) {
        Disbursement disbursement = new Disbursement();
        disbursement.setAccountNumber(responseDisbursementModel.getAccountNumber());
        disbursement.setAmount(responseDisbursementModel.getAmount());
        disbursement.setBankCode(responseDisbursementModel.getBankCode());
        disbursement.setBeneficiaryName(responseDisbursementModel.getBeneficiaryName());
        disbursement.setFee(responseDisbursementModel.getFee());
        disbursement.setReceipt(responseDisbursementModel.getReceipt());
        disbursement.setRemark(responseDisbursementModel.getRemark());
        disbursement.setStatus(responseDisbursementModel.getStatus());
        disbursement.setTimeServed(responseDisbursementModel.getTimeServed());
        disbursement.setTransactionId(responseDisbursementModel.getTransactionId());
        disbursement.setTimestamp(responseDisbursementModel.getTimestamp());
        disbursementRepository.save(disbursement);
    }

    public Disbursement findDisbursementByTransactionId(String transactionId) {
        Disbursement disbursement = disbursementRepository
                .findByTransactionId(new BigInteger(transactionId));
        return disbursement;

    }

    public void updateDataDisbursementCheck(Disbursement disbursement, ResponseDisbursementModel responseDisbursementModel){
        disbursement.setStatus(responseDisbursementModel.getStatus());
        disbursement.setTimeServed(responseDisbursementModel.getTimeServed());
        disbursement.setReceipt(responseDisbursementModel.getReceipt());
        disbursementRepository.save(disbursement);
    }

}
