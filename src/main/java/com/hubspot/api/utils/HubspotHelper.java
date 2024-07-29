package com.hubspot.api.utils;

import com.hubspot.api.model.CallRecords;
import com.hubspot.api.model.ConcurrentCalls;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.hubspot.api.utils.HubspotConstants.DATE_FORMAT;

@Component
public class HubspotHelper {


    public static String convertTimestampToDate(long timestamp) {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date(timestamp));
    }

    public List<ConcurrentCalls> calculateConcurrentCalls(List<CallRecords> callRecords) {
        Map<String, List<CallRecords>> recordsByCustomerAndDate = new HashMap<>();
        for (CallRecords record : callRecords) {
            String date = HubspotHelper.convertTimestampToDate(record.getStartTimestamp());
            String key = record.getCustomerId() + "-" + date;
            recordsByCustomerAndDate.putIfAbsent(key, new ArrayList<>());
            recordsByCustomerAndDate.get(key).add(record);
        }

        List<ConcurrentCalls> results = new ArrayList<>();
        for (Map.Entry<String, List<CallRecords>> entry : recordsByCustomerAndDate.entrySet()) {
            String[] parts = entry.getKey().split("-",2);

            System.out.println(parts.length);
            int customerId = Integer.parseInt(parts[0]);
            String date = parts[1];
            System.out.println(date);

            List<CallRecords> records = entry.getValue();
            int maxConcurrentCalls = 0;
            long timestamp = 0;
            Set<String> callIdsAtPeak = new HashSet<>();

            for (CallRecords record : records) {
                long currentTimestamp = record.getStartTimestamp();
                Set<String> ongoingCalls = new HashSet<>();
                for (CallRecords rec : records) {
                    if (rec.getStartTimestamp() <= currentTimestamp && rec.getEndTimestamp() > currentTimestamp) {
                        ongoingCalls.add(rec.getCallId());
                    }
                }
                if (ongoingCalls.size() > maxConcurrentCalls) {
                    maxConcurrentCalls = ongoingCalls.size();
                    timestamp = currentTimestamp;
                    callIdsAtPeak = ongoingCalls;
                }
            }

            results.add(new ConcurrentCalls(customerId, date, maxConcurrentCalls, timestamp, new ArrayList<>(callIdsAtPeak)));
        }

        return results;
    }
}
