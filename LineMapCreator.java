package part010_objectvile_travel_final_revised;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


// File that creates a Transportation LineMap from the file input
public class LineMapCreator implements LineInfoProcessor{

	private static final String UNDEFINED ="-1";
	private Map<String, Line> lineMap;
	private LineType type = null;
    private String lineName = null;
    private boolean round = false;
    private List <String> stationNames = new LinkedList<>();
    private String token=UNDEFINED;
    String lastToken = UNDEFINED;
	
	public LineMapCreator ()
	{
		 lineMap = new HashMap<>();
	}
	
	@Override
	public void processInputLine(String line) {
		// check if line is a comment line
    	if (line.trim().startsWith("//"))
    	{
    		return;
    	}
    	// if line contains ":" means that line contains a token
    	// currently supported tokens
    	/* 
    	 * type: (supported value Subway) the type has to be specified at the beginning at least once before line or station names are entered 
    	 * name: (name of the line any unique value can be same as one of the station Names) all station names that follow the given line name belong to this line
    	 * round: (specifies whether given line is circular) this token has to appear right after line name, but is optional
    	 * 
    	 */
    	// all non-white character only lines that do not start with token specify station names
    	// LineType has to be specified at least once before entering station names
    	// line name has to be provided before the station names
    	// when type: or name: token appear after a list of line it assumes that the previous line is completed
    	
    	if (line.contains(":"))
    	{
    		
    		token = (line.substring(0, line.indexOf(":"))).toLowerCase();
    		String tokenValue = (line.substring(line.indexOf(":")+2, line.length())).trim();
    		switch (token)
    		{
    		case "type":
    			if (!stationNames.isEmpty())
    			{
    				lineMap.put(lineName, new Line (type, lineName, stationNames, round));
    				stationNames = new LinkedList<>();
    				round = false;
    				lineName = null;
    			}
    			if (tokenValue.equalsIgnoreCase(LineType.SUBWAY.toString()))
    				type = LineType.SUBWAY;
    			else
    				throw new RuntimeException("Unknown Line Type");
    			lastToken = token;
    			break;
    		case "name":
    			if (lineMap.containsKey(tokenValue))
    				throw new RuntimeException("Each line must have unique name");
    			if (!stationNames.isEmpty())
    			{
    				lineMap.put(lineName, new Line (type, lineName, stationNames, round));
    				stationNames = new LinkedList<>();
    				round = false;
    			}
    			lineName = tokenValue;
    			lastToken = token;
    			break;
    		case "round":
    			if (!lastToken.equalsIgnoreCase("name"))
    				throw new RuntimeException("Specify wheter the line is round right after line name");
    			if (tokenValue.equalsIgnoreCase("true"))
    				round = true;
    			else 
    				round = false;
    			
    			lastToken = token;
    		}
    	}
    	else
    	{
    		token = null;
    		lastToken =UNDEFINED;
    		
    		if (type!=null && lineName!=null)
    			if (line.trim().length()!=0)
    				stationNames.add(line);
    		else
    			throw new RuntimeException("The line type and name has to be defined before specifying station names");
    			
    	}
    }
	
	
	public LineNetwork getNetwork()
	{
		return new LineNetwork(Collections.unmodifiableMap(lineMap));
	}


	@Override
	public void endProcessing() {
		if (!stationNames.isEmpty())
		{
			lineMap.put(lineName, new Line (type, lineName, stationNames, round));
		}
		
	}
	
	


}
