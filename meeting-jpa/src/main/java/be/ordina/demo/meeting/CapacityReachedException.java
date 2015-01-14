package be.ordina.demo.meeting;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CapacityReachedException extends RuntimeException {
}
