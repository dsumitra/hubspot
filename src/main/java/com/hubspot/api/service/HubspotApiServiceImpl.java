package com.hubspot.api.service;

import com.hubspot.api.dao.IHubspotDao;
import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.ConcurrentCalls;
import com.hubspot.api.utils.HubspotHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubspotApiServiceImpl implements IHubspotApiService {

    @Autowired
    private IHubspotDao hubspotDao;

    @Autowired
    private HubspotHelper hubspotHelper;

    @Override
    public List<CallRecords> getCallRecordsList() {
        List<CallRecords> callRecordsList = hubspotDao.getCallRecords();
        return callRecordsList;
    }

    @Override
    public List<ConcurrentCalls> getConcurrentCallsList(List<CallRecords> callRecordsList) {
        return hubspotHelper.calculateConcurrentCalls(callRecordsList);
    }

    @Override
    public void sendConcurrentCalls(List<ConcurrentCalls> concurrentCalls) {
         hubspotDao.sendConcurrentCalls(concurrentCalls);
    }
}
