package com.hubspot.api.dao;

import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.ConcurrentCalls;

import java.util.List;

public interface IHubspotDao {

    List<CallRecords> getCallRecords();

    void sendConcurrentCalls(List<ConcurrentCalls> concurrentCallsList);
}
