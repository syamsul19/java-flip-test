package com.test.flip.Disbursement.service;

import com.test.flip.Disbursement.dto.DisbursementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class HitToServerService {

    public ResponseEntity<String> send(HttpMethod httpMethod, String url, HttpEntity httpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("request : " + httpEntity);
        System.out.println("url : " + url);

        ResponseEntity<String> response =
                restTemplate.exchange(url,
                        httpMethod,
                        httpEntity,
                        String.class);
        return response;
    }

}
