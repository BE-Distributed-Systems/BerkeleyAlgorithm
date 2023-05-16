package client;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import common.AppConstants;
import server.TimeServer;
import server.TimeServerImpl;

/**
 * Client-Side
 * 
 * @author Prabhat Kumar
 */
public class MainClock {

	public static void main(String[] args) {
		try {
			List<LocalTime> times = new ArrayList<LocalTime>();

			LocalTime localTime = LocalTime.parse("07:00:00", AppConstants.formatter);
			times.add(localTime);
			System.out.println("Local Time: " + formatter.format(localTime));

			// Server connection 1
			Registry registry1 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_1, AppConstants.SERVER_PORT_1);
			TimeServer hs1 = (TimeServer) registry1.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 1 successfully established.");
			LocalTime timeServer1 = hs1.getTime();
			times.add(timeServer1);
			System.out.println("Server Time 1: " + formatter.format(timeServer1));

			// Server connection 2
			Registry registry2 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_2, AppConstants.SERVER_PORT_2);
			TimeServer hs2 = (TimeServer) registry2.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 2 successfully established.");
			LocalTime timeServer2 = hs2.getTime();
			times.add(timeServer2);
			System.out.println("Server Time 2: " + formatter.format(timeServer2));

			// Server connection 3
			Registry registry3 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_3, AppConstants.SERVER_PORT_3);
			TimeServer hs3 = (TimeServer) registry3.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 3 successfully established.");
			LocalTime timeServer3 = hs3.getTime();
			times.add(timeServer3);
			System.out.println("Server Time 3: " + formatter.format(timeServer3));

			long nanoLocal = localTime.toNanoOfDay();
			long diffServer1 = timeServer1.toNanoOfDay() - nanoLocal;
			long diffServer2 = timeServer2.toNanoOfDay() - nanoLocal;
			long diffServer3 = timeServer3.toNanoOfDay() - nanoLocal;
			long avgDiff = (diffServer1 + diffServer2 + diffServer3) / 3; 

			// Assign New Date
			hs1.adjustTime(localTime, avgDiff);
			hs2.adjustTime(localTime, avgDiff);
			hs3.adjustTime(localTime, avgDiff);
			localTime = localTime.plusNanos(avgDiff);
			System.out.println("updated schedules");

			// Check time in all instances
			System.out.println("Time Local: " + formatter.format(localTime));
			System.out.println("Time Server 1: " + formatter.format(hs1.getTime()));
			System.out.println("Time Server 2: " + formatter.format(hs2.getTime()));
			System.out.println("Time Server 3: " + formatter.format(hs3.getTime()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}