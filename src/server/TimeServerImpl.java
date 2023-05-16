package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import common.AppConstants;

/**
 * Implementation of {@link TimeServer}
 * 
 * @author Prabhat Kumar
 */
public class TimeServerImpl extends UnicastRemoteObject implements TimeServer {

	private static final long serialVersionUID = -6810169856453308607L;

	private LocalTime localTime;

	public TimeServerImpl(LocalTime localTime) throws RemoteException {
		this.localTime = localTime;
	}

	@Override
	public LocalTime getTime() throws RemoteException {
		return localTime;
	}

	@Override
	public void adjustTime(LocalTime localTime, long diffNanos) throws RemoteException {
		long localNanos = localTime.toNanoOfDay();
		long thisNanos = this.getTime().toNanoOfDay();
		long newNanos = thisNanos - localNanos;
		newNanos = newNanos * -1 + diffNanos + thisNanos;
		LocalTime newLocalTime = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("Updated Schedule: " + AppConstants.formatter.format(newLocalTime));
		this.localTime = newLocalTime;
	}

}