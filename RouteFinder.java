package part010_objectvile_travel_final_revised;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class RouteFinder {
	private String destination;
	private String departure;
	
	//private NextStationMap nextStationsMap;
	private Map<String,Boolean> usedStations;
	private Map<String,LineStationPair> routeMap;
	private Queue<String> processingQueue;
	private Stack<LineStationPair> route;
	
	
	
	
	
	public RouteFinder(NextStationMap nextStationsMap, String departure, String destination)
	{
		if (!nextStationsMap.contains(departure))
			throw new RuntimeException("The name selected for departure station is invalid");
		if (!nextStationsMap.contains(destination))
			throw new RuntimeException("The name selected for destination station is invalid");
		this.destination= destination;
		this.departure = departure;
		//this.nextStationsMap = nextStationsMap;
		
		processingQueue = new LinkedList<>();
		usedStations = new HashMap<>();
		usedStations.put(departure, true);
		routeMap = new HashMap<>();
		route = new Stack<>();
		
		System.out.println("Finished routeFinder setup");
		
	}
	
	public void findRoute(NextStationMap nextStationsMap)
	{
		String currentStation = departure;
		
		while (!currentStation.equalsIgnoreCase(destination))
		{
			System.out.println("Processing station " + currentStation);
			List<LineStationPair> possibleNextStations = nextStationsMap.getNextStations(currentStation);
			for (LineStationPair possibleNextStationName : possibleNextStations)
			{
				String nextStationName = possibleNextStationName.getStationName();
				if (!usedStations.containsKey(nextStationName))
				{
					
					usedStations.put(nextStationName,true);
					routeMap.put(nextStationName, new LineStationPair (possibleNextStationName.getLineName(), currentStation));
					processingQueue.add(nextStationName);
					System.out.println("Added station " + nextStationName + " to processing queue");
				}
				
			}
			if (processingQueue.isEmpty())
				break;
			else
				currentStation = processingQueue.remove();
		}
		// Route is now found but needs to be extracted
		if (!routeMap.containsKey(destination)) // could not reach destination from the starting station or destination same as departure
			return;
		LineStationPair pair= routeMap.get(destination);
		
		do
		{
			route.add(pair);
			pair = routeMap.get(pair.getStationName());
			
		}
		while(pair!=null);
	}
	
	
	
	public void printRoute(LineNetwork lineNetwork)
	{
		if (destination.equalsIgnoreCase(departure))
			System.out.println("The destination is same as departure, you do not need to travel");
		
		else if (!routeMap.containsKey(destination))
		{
			System.out.println("The station " + destination + " is not reachable from " +departure);
		}
			
		else
		{
			LineStationPair pair = route.pop();
			String stationName = pair.getStationName();
			String lineName = pair.getLineName();
			String prevLine = lineName;
			System.out.println("Enter " + lineNetwork.getLine(lineName).getLineType() + " line " + lineName + " at station " + stationName);
			
			while (!route.isEmpty())
			{
				pair = route.pop();
				stationName = pair.getStationName();
				lineName = pair.getLineName();
				
				System.out.println("Proceed to station " + stationName);
				if (!lineName.equalsIgnoreCase(prevLine))
					System.out.println("... and then change to " +lineNetwork.getLine(lineName).getLineType() + " line " + lineName );
				
				prevLine = lineName;
				
				
			}
			System.out.println("Proceed to station " + destination + " and arrive at your destination!");
				
			
		}
		/*while(!route.isEmpty())
		{
			System.out.println(route.pop().stationName);
		}*/
	}
}
