package part010_objectvile_travel_final_revised;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NextStationMap {
	private Map<String, List<LineStationPair>> nextStationMap;
	
	public NextStationMap ()
	{
		nextStationMap = new HashMap<>();
	}
	
	public void createNextStationMap (LineNetwork network)
	{
		for (String lineName: network.getLineNames())
		{
			Line currentLine = network.getLine(lineName);
			for (int stationIndex = 0; stationIndex < currentLine.length(); stationIndex++)
			{
				String stationName = currentLine.getStationNames().get(stationIndex);
				LineStationPair prev = new LineStationPair(currentLine.getLineName(), currentLine.getStationNames().get(currentLine.prevStation(stationIndex)));
				LineStationPair next = new LineStationPair(currentLine.getLineName(), currentLine.getStationNames().get(currentLine.nextStation(stationIndex)));

				List<LineStationPair> nextStations = nextStationMap.get(stationName);
				if (nextStations == null)
				{
					nextStations = new LinkedList<>();
				}
				nextStations.add(prev);
				if (currentLine.length() >2) // more than two stations on a line
					nextStations.add(next);
				nextStationMap.put(stationName, nextStations);
			}
		}
	}
	
	public boolean contains (String stationName)
	{
		return nextStationMap.containsKey(stationName);
	}
	
	/*public void addLineStationPair (Line currentLine, int stationIndex)
	{
		LineStationPair pair = new LineStationPair(currentLine.getLineName(), currentLine.getStationNames().get(stationIndex));
		LineStationPair prev = new LineStationPair(currentLine.getLineName(), currentLine.getStationNames().get(currentLine.prevStation(stationIndex)));
		LineStationPair next = new LineStationPair(currentLine.getLineName(), currentLine.getStationNames().get(currentLine.nextStation(stationIndex)));

		List<LineStationPair> nextStations = new LinkedList<>();
		nextStations.add(next);
		if (!prev.equals(next)) // if more than two stations on the line
			nextStations.add(prev);
		nextStationMap.put(pair, nextStations);
	}*/
	
	/*public Set<LineStationPair> getAllPairs()
	{
		return Collections.unmodifiableSet(nextStationMap.keySet());
	}*/
	
	/*public void addNextStations (LineStationPair key, List<LineStationPair> newValue)
	{
		List<LineStationPair> nextStations = nextStationMap.get(key);
		nextStations.addAll(newValue);
		nextStationMap.put(key, nextStations);
	}*/
	
	public void printNextStations()
	{
		for (String stationName: nextStationMap.keySet())
		{
			System.out.println();
			System.out.println();
			System.out.println("Station is " + stationName);
			System.out.println("Next Stations are: ");
			List <LineStationPair> stations = nextStationMap.get(stationName);
			for (LineStationPair pair:stations)
				System.out.println(pair.getStationName() + " on line " +pair.getLineName());
			
		}
	}
	public List<LineStationPair> getNextStations (String currentStation)
	{
		return Collections.unmodifiableList(nextStationMap.get(currentStation));
	}
}
