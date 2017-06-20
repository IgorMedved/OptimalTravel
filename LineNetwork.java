package part010_objectvile_travel_final_revised;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LineNetwork {
	private Map<String, Line> mNetwork;
	
	public LineNetwork(Map<String,Line> network)
	{
		mNetwork = network;
	}
	
	public void printLines ()
	{
		for (String lineName: mNetwork.keySet())
			System.out.println(mNetwork.get(lineName));
	}
	
	public Set<String> getLineNames ()
	{
		return Collections.unmodifiableSet(mNetwork.keySet());
	}
	
	public Line getLine(String lineName)
	{
		return mNetwork!=null? mNetwork.get(lineName):null;
	}
}
