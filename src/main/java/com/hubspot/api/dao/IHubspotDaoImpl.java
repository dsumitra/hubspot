package com.hubspot.api.dao;

import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.CallRecordsWrapper;
import com.hubspot.api.model.ConcurrentCalls;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Repository
public class IHubspotDaoImpl implements IHubspotDao {

    @Value("${hubspot.api.get.callRecords.url}")
    private String getCallRecords;

    @Value("${hubspot.api.post.concurrentCalls.url}")
    private String sendConcurrentCalls;

    @Override
    public List<CallRecords> getCallRecords() {
        RestTemplate restTemplate = new RestTemplate();
        CallRecordsWrapper result = restTemplate.getForObject(getCallRecords, CallRecordsWrapper.class);
        return result.getCallRecords();
    }

    @Override
    public void sendConcurrentCalls(List<ConcurrentCalls> concurrentCalls) {
        try {

            RestTemplate restTemplate = new RestTemplate();
             restTemplate.postForEntity(sendConcurrentCalls, Collections.singletonMap("results", concurrentCalls), String.class);

        } catch (HttpClientErrorException ex) {
            System.out.println("Exception status code: " + ex.getStatusCode());
            System.out.println("Exception response body: " + ex.getResponseBodyAsString());
            System.out.println("Exception during sending concurrent calls post request: " + ex.getMessage());
        }
    }


}
