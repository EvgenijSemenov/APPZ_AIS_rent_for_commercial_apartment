package util;

import entity.Lease;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaseUtil {

    public static List<Lease> getActive(List<Lease> leaseList) {
        List<Lease> activeLeases = new ArrayList();
        for (Lease lease: leaseList) {
            if (lease.getEndDate().isAfter(LocalDateTime.now())) {
                activeLeases.add(lease);
            }
        }
        return activeLeases;
    }

}
