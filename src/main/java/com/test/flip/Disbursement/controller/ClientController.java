package com.test.flip.Disbursement.controller;

import com.test.flip.Disbursement.dto.DisbursementDto;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.service.FlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disbursement")
public class ClientController {

    @Autowired
    FlipService flipService;

    @PostMapping
    public ResponseEntity<ResponseDisbursementModel> doDisbursement(@RequestParam("bank_code") String bankCode,
                                                                    @RequestParam("account_number") String accountNumber,
                                                                    @RequestParam("amount") Integer amount,
                                                                    @RequestParam("remark") String remark) {
        DisbursementDto disbursementDto = new DisbursementDto(bankCode, accountNumber, amount, remark);
        ResponseDisbursementModel response = flipService.doDisbursement(disbursementDto);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<ResponseDisbursementModel> getStatusDisbursement(@PathVariable("transaction_id") String transactionId) {
        ResponseDisbursementModel response = flipService.doCheckStatus(transactionId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
