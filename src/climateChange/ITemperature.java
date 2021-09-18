package climateChange;

public interface ITemperature extends Comparable<ITemperature> {
	
	// get the name of the country
	public String getCountry();
	
	// get the 3-letter code of the country
	public String getCountry3LetterCode(); 
	
	// get the month
	public String getMonth(); 
	
	// get the year
	public int getYear(); 
	
	// get temperature; input parameter of false = return Celsius value)
	public double getTemperature(boolean getFahrenheit);
	
}
