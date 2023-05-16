package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ClockOne {

	public static void main(String[] args) {
		try {
			// Server 1
			TimeServer hs1 = new TimeServerImpl(LocalTime.parse("07:05:00", formatter));
			Registry registry1 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_1);
			registry1.rebind(TimeServerImpl.class.getSimpleName(), hs1);
			System.out.println(String.format("Server 1 started on port %s", AppConstants.SERVER_PORT_1));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
