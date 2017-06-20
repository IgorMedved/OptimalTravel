package part010_objectvile_travel_final_revised;

import java.util.Collections;
import java.util.List;

// This file stores information about a line on a transportation network including
// String @lineName
// List of stations @stationNames
// LineType @lineType i.e subway, bus, etc
// boolean isRoundLine (if starting line is the same as end line)
public class Line {
	public static final int UNDEFINED_STATION = -1;
	
	private LineType lineType;
	private String lineName;
	private List <String> stationNames;
	private boolean isRoundLine;
	
	
	public Line(LineType lineType, String lineName, List<String> stationNames, boolean isRoundLine) {
		super();
		setLineType(lineType);
		this.lineName = lineName;
		setStationNames(stationNames);
		this.isRoundLine = isRoundLine;
	}
	
	private void setLineType (LineType lineType)
	{
		if (lineType == null)
				throw new RuntimeException("Line type was not initialized. Add Type: Linetype to lineInfo file before first line name");
		this.lineType = lineType;
	}
	
	private void setStationNames (List<String> stationNames)
	{
		if (stationNames == null || stationNames.isEmpty())
		{
			throw new RuntimeException("The station list was empty for line " + lineName);
		}
		else if (stationNames.size()==1)
		{
			throw new RuntimeException("There must be more than one line in a valid station. Occured in " +lineName );
		}
		this.stationNames = stationNames;
	}
	
	
	// returns previous Station if it exists or UNDEFINED_STATION
	// since stationNames are stored in an ArrayList the previousStation
	// for station with index 0 on a round Line
	// is station with index stationNames.size() -1;
	public int prevStation (int currentIndex)
	{
		if (isAdequateLineSize())
		{
			if(currentIndex >0 && currentIndex <stationNames.size())
				return currentIndex -1;
			else if (currentIndex == 0 && isRoundLine)
				return stationNames.size()-1;
		}
		return UNDEFINED_STATION;
	}
	
	// returns next Station if it exists or UNDEFINED_STATION
	// since stationNames are stored in an ArrayList therefore the next Station for station with
	// index stationNames.size() -1 on a round line
	// is station with index 0;
	public int nextStation (int currentIndex)
	{
		if (isAdequateLineSize())
		{
			if (currentIndex >= 0 && currentIndex < stationNames.size()-1)
				return currentIndex +1;
			else if (currentIndex == stationNames.size()-1 && isRoundLine)
				return 0;
		}
		return UNDEFINED_STATION;
	}
	
	// Line should have at least two stations
	private boolean isAdequateLineSize()
	{
		return stationNames!=null && stationNames.size() > 1;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder ("Line Type: ");
		sb.append(lineType);
		sb.append("\nLine name: ");
		sb.append(lineName);
		sb.append("\nStation names:\n");
		for (String stationName:stationNames)
		{
			sb.append(stationName);
			sb.append("\n");
		}
		if (isRoundLine)
			sb.append(stationNames.get(0));
		return sb.toString();
	}
	
	public List<String> getStationNames()
	{
		return Collections.unmodifiableList(stationNames);
	}
	
	public String getLineName()
	{
		return lineName;
	}
	
	public boolean isRound()
	{
		return isRoundLine;
	}
	
	public LineType getLineType()
	{
		return lineType;
	}

	public int length()
	{
		return stationNames !=null? stationNames.size():0;
	}

}
