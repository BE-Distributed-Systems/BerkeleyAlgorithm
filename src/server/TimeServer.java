package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

/**
 * Interface to the Client-Side access the methods
 * 
 * @author Prabhat Kumar
 */
public interface TimeServer extends Remote {

	LocalTime getTime() throws RemoteException;

	void adjustTime(LocalTime localTime, long nanos) throws RemoteException;
}