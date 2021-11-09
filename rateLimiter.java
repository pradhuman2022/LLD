import java.util.*;
import java.time.*;

public class Main {

    public static void main(String arg[]) {
        
        RateLimit r = new RateLimit();
        r.addCustomer("pradhuman", 5, 100);
        
        for (int i = 0; i < 1000; i++) {
                System.out.println(r.rateLimit("pradhuman"));
        }
    }
}

class RateLimit {
                    // seconds  No of reuqest
    Map<String, Map<Integer, Integer>> customerToRequestTimeMap;
    
    // cId.     //lastTimestamp //remaing no of request
    Map<String, Map<Instant, Integer>> bucket;
    
    public RateLimit() {
        customerToRequestTimeMap = new HashMap<>();
        bucket = new HashMap<>();    
    }
 
    public boolean addCustomer(String customerId, Integer seconds, Integer requests) {
        try {
            Map<Integer, Integer> secondsToRequestMap = new HashMap<>();
            secondsToRequestMap.put(seconds, requests);
            customerToRequestTimeMap.put(customerId, secondsToRequestMap);
            
            Map<Instant, Integer> lastTimeToRequestMap = new HashMap<>();
            lastTimeToRequestMap.put(Instant.now(), requests);

            bucket.put(customerId, lastTimeToRequestMap);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    boolean rateLimit(String customerId) {
        
        Instant currentTime = Instant.now();;
        
        Map<Integer, Integer> customerTrafficRequirement = customerToRequestTimeMap.get(customerId);
        
        int allowedTime = 0;
        int allowedRequest = 0;
        
        for (Map.Entry<Integer, Integer> entry: customerTrafficRequirement.entrySet()) {
            allowedTime = entry.getKey();
            allowedRequest = entry.getValue();
        }
    
        Map<Instant, Integer> bucketDetailForCustomer = bucket.get(customerId);
        
        Instant customerTimerDetails = null;
        int remainingRequest = 0;
        for (Map.Entry<Instant, Integer> entry: bucketDetailForCustomer.entrySet()) {
            customerTimerDetails = entry.getKey();
            remainingRequest = entry.getValue();
        }
    
        Duration timeElapsed = Duration.between(customerTimerDetails, currentTime);
        long elapsedTime = timeElapsed.toSeconds();
        
        System.out.println(elapsedTime + " " + allowedRequest);
        
        if (elapsedTime > allowedTime) {
            return giveTokenRequestFor(customerId, allowedRequest, currentTime);
        } else {
            return giveTokenRequestFor(customerId, remainingRequest, currentTime);
        } 
    }
    
    
    public boolean giveTokenRequestFor (String customerId, int remainingRequest, Instant lastTime) {
        
        if (remainingRequest <= 0)
            return false;
    
        remainingRequest--;
        Map<Instant, Integer> lastTimeToRequestMap = new HashMap<>();
        lastTimeToRequestMap.put(lastTime, remainingRequest);
        bucket.put(customerId, lastTimeToRequestMap);
    
        return true;
    }
}
