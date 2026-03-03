package com.JobSchedulingNotification.JobSchedulingProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WebHookService {


    private final RestTemplate restTemplate= new RestTemplate();


    public String send(String webhookUrl, String message){

        Map<String, String> payload=new HashMap<>();

        payload.put("text",message);

        return restTemplate.postForObject(webhookUrl,payload,String.class);
    }



}
