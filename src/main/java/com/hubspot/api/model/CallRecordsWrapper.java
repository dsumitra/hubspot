package com.hubspot.api.model;

import java.util.List;

public class CallRecordsWrapper {

    private List<CallRecords> callRecords;

    public List<CallRecords> getCallRecords() {return callRecords;}

    public void setCallRecords(List<CallRecords> callRecords) {
        this.callRecords = callRecords;
    }
}
