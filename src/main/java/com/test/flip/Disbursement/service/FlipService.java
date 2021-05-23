package com.test.flip.Disbursement.service;

import com.google.gson.Gson;
import com.test.flip.Disbursement.dto.DisbursementDto;
import com.test.flip.Disbursement.entity.Disbursement;
import com.test.flip.Disbursement.model.ResponseDisbursementModel;
import com.test.flip.Disbursement.service.entity.DisbursementEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlipService {

    @Autowired
    HitToServerService hitToServerService;

    @Autowired
    DisbursementEntityService disbursementEntityService;

    @Autowired
    PrepareDataService prepareDataService;

    ResponseDisbursementModel responseDisbursementModel;
    Gson gson = new Gson();

    @Value("${base.url.server:xxx}")
    public String baseUrl;


    public ResponseDisbursementModel doDisbursement(DisbursementDto disbursementDto) {

        HttpEntity entity = prepareDataService.setEntityForDisbursement(disbursementDto);
        ResponseEntity<String> response = hitToServerService.send(HttpMethod.POST, baseUrl, entity);
        System.out.println("response : " + response.getBody());
        responseDisbursementModel = gson.fromJson(response.getBody(), ResponseDisbursementModel.class);
        disbursementEntityService.storeData(responseDisbursementModel);
        return responseDisbursementModel;
    }

    public ResponseDisbursementModel doCheckStatus(String transactionId) {
        Disbursement disbursement = disbursementEntityService.findDisbursementByTransactionId(transactionId);

        HttpEntity entity = prepareDataService.setEntityForCheckDisbursement();
        ResponseEntity<String> response = hitToServerService.send(HttpMethod.GET, baseUrl + "/" + transactionId, entity);
        System.out.println("response : " + response.getBody());
        responseDisbursementModel = gson.fromJson(response.getBody(), ResponseDisbursementModel.class);
        disbursementEntityService.updateDataDisbursementCheck(disbursement, responseDisbursementModel);
        return responseDisbursementModel;
    }


}
