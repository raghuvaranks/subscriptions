package com.fs.subscriptions.subscriptions.service.impl;

import com.fs.subscriptions.subscriptions.exception.ResourceNotFoundException;
import com.fs.subscriptions.subscriptions.payload.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerValidationImpl {
    @Autowired
    private RestTemplate restTemplate;

    public CustomerDTO validateCustomer(Long custId){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity=new HttpEntity<>(headers);

        Map mp=new HashMap<>();
        mp.put("custId",custId);
        ResponseEntity<CustomerDTO> responseEntity=restTemplate.exchange("http://localhost:8083/custdetails/{custId}", HttpMethod.GET,requestEntity,CustomerDTO.class);
        if(responseEntity.getStatusCode().value()==200){
            return responseEntity.getBody();
        }else{
            throw new ResourceNotFoundException("Customer Not Found for ID "+custId.toString());
        }
    }
}
