package com.hubspot.api.controller;

import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.ConcurrentCalls;
import com.hubspot.api.service.IHubspotApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hubspot/api/")
public class HubspotApiController {

    @Autowired
    private IHubspotApiService hubspotApiService;

    private List<CallRecords> callRecordsList;

    private List<ConcurrentCalls> concurrentCallsList;


    @ApiOperation(value = "method to get list of call records, transform the data and send post request for concurrent calls.")
    @RequestMapping(value = "/" + "calls", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void getRecordsAndSendConcurrentCalls() {
        callRecordsList = hubspotApiService.getCallRecordsList();

        if (CollectionUtils.isEmpty(callRecordsList)) {
            System.out.println("Unable to get customer  information");
        }

        concurrentCallsList = hubspotApiService.getConcurrentCallsList(callRecordsList);
         hubspotApiService.sendConcurrentCalls(concurrentCallsList);
    }
}
