package com.hubspot.api.service;

import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.ConcurrentCalls;

import java.util.List;

public interface IHubspotApiService {

    List<CallRecords> getCallRecordsList();

    List<ConcurrentCalls> getConcurrentCallsList(List<CallRecords> callRecordsList);

    void sendConcurrentCalls(List<ConcurrentCalls> concurrentCalls);

}
