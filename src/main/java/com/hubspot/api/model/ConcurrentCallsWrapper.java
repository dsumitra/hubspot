package com.hubspot.api.model;

import java.util.List;

public class ConcurrentCallsWrapper {

    private List<ConcurrentCalls> concurrentCalls;

    public List<ConcurrentCalls> getConcurrentCalls() {
        return concurrentCalls;
    }

    public void setConcurrentCalls(List<ConcurrentCalls> concurrentCalls) {
        this.concurrentCalls = concurrentCalls;
    }
}
