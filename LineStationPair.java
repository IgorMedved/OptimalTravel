package part010_objectvile_travel_final_revised;

public class LineStationPair {
	private String lineName;
	private String stationName;
	
	public LineStationPair (String lineName, String stationName)
	{
		this.lineName = lineName;
		this.stationName = stationName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineName == null) ? 0 : lineName.hashCode());
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineStationPair other = (LineStationPair) obj;
		if (lineName == null) {
			if (other.lineName != null)
				return false;
		} else if (!lineName.equals(other.lineName))
			return false;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}

	public String getLineName() {
		return lineName;
	}

	public String getStationName() {
		return stationName;
	}

	

}
