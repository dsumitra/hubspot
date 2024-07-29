package com.hubspot.api.model;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentCalls {

    private int customerId;
    private String date;
    private int maxConcurrentCalls;
    private long timestamp;
    private List<String> callIds;

    public ConcurrentCalls(int customerId, String date, int maxConcurrentCalls, long timestamp, List<String> callIds) {
        this.customerId = customerId;
        this.date = date;
        this.maxConcurrentCalls = maxConcurrentCalls;
        this.timestamp = timestamp;
        this.callIds = callIds;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxConcurrentCalls() {
        return maxConcurrentCalls;
    }

    public void setMaxConcurrentCalls(int maxConcurrentCalls) {
        this.maxConcurrentCalls = maxConcurrentCalls;
    }

    public List<String> getCallIds() {
        return callIds;
    }

    public void setCallIds(List<String> callIds) {
        this.callIds = callIds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ConcurrentCalls{" +
                "customerId=" + customerId +
                ", date='" + date + '\'' +
                ", maxConcurrentCalls=" + maxConcurrentCalls +
                ", timestamp=" + timestamp +
                ", callIds=" + callIds +
                '}';
    }
}
