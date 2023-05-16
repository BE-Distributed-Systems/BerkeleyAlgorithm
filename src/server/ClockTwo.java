package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ClockTwo {

	public static void main(String[] args) {
		try {
			// Server 2
			TimeServer hs2 = new TimeServerImpl(LocalTime.parse("07:10:00", formatter));
			Registry registry2 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			registry2.rebind(TimeServerImpl.class.getSimpleName(), hs2);
			System.out.println(String.format("Server 2 started on port %s", AppConstants.SERVER_PORT_2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
