package com.test.flip.Disbursement.controller;

import com.test.flip.Disbursement.entity.Disbursement;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.service.entity.DisbursementEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/disburse")
public class TestController {

    @Autowired
    DisbursementEntityService disbursementEntityService;

    @PostMapping
    public ResponseEntity<ResponseDisbursementModel> doReceive(@RequestParam("bank_code") String bankCode,
                                                               @RequestParam("account_number") String accountNumber,
                                                               @RequestParam("amount") Integer amount,
                                                               @RequestParam("remark") String remark) {
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
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDisbursementModel);

    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<ResponseDisbursementModel> getStatusDisbursement(@PathVariable("transaction_id") String transactionId) {
        ResponseDisbursementModel responseDisbursementModel = new ResponseDisbursementModel();
        responseDisbursementModel.setAccountNumber("1234567890");
        responseDisbursementModel.setAmount(10000);
        responseDisbursementModel.setBankCode("bni");
        responseDisbursementModel.setBeneficiaryName("PT FLIP");
        responseDisbursementModel.setFee(4000);
        responseDisbursementModel.setReceipt("https://flip-receipt.oss-ap-southeast-5.aliyuncs.com/debit_receipt/126316_3d07f9fef9612c7275b3c36f7e1e5762.jpg");
        responseDisbursementModel.setRemark("sample remark");
        responseDisbursementModel.setStatus("SUCCESS");
        responseDisbursementModel.setTimeServed("2019-05-21 09:26:11");
        responseDisbursementModel.setTimestamp("2019-05-21 09:12:42");
        responseDisbursementModel.setTransactionId(new BigInteger("5535152564"));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDisbursementModel);
    }


    @GetMapping("/test/{transaction_id}")
    public ResponseEntity<String> getTestStatusDisbursement(@PathVariable("transaction_id") String transactionId) {
        Disbursement disbursement = disbursementEntityService.findDisbursementByTransactionId(transactionId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(disbursement.getStatus());
    }





}
