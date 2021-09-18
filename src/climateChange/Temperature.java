package climateChange;

import java.util.*;
import java.text.*;

public class Temperature implements ITemperature {
	
	private String country;
	private String countryCode;
	private String month;
	private int year;
	private double temperature;
	private HashMap<String, Integer> months;
	DecimalFormat df = new DecimalFormat("#.##"); // formats doubles to two decimal places
	
	// Constructor for the Temperature object
	// Initializes all instance variables
	public Temperature(double temperature, int year, String month, String country, String countryCode) 
	{
		this.temperature = temperature;
		this.year = year;
		this.month = month;
		this.country = country;
		this.countryCode = countryCode;
		months = new HashMap<String, Integer>(); // Creates a hashmap to map months to numbers
		months.put("Jan", 1);
		months.put("Feb", 2);
		months.put("Mar", 3);
		months.put("Apr", 4);
		months.put("May", 5);
		months.put("Jun", 6);
		months.put("Jul", 7);
		months.put("Aug", 8);
		months.put("Sep", 9);
		months.put("Oct", 10);
		months.put("Nov", 11);
		months.put("Dec", 12);
	}
	
	// Returns the country name
	public String getCountry()
	{
		return country;
	}

	// Returns the country code
	public String getCountry3LetterCode() 
	{
		return countryCode;
	}

	// Returns the month
	public String getMonth() 
	{
		return month;
	}

	// Returns the year
	public int getYear() 
	{
		return year;
	}

	// Returns the temperature in either celsius or fahrenheit
	public double getTemperature(boolean getFahrenheit)
	{
		if (getFahrenheit)
		{
			double fahrenheit = temperature * 1.8;
			return fahrenheit + 32;
		}
		return temperature;
	}
	
	// Overrides the toString method
	// Returns a string interpretation of the temperature object
	public String toString()
	{
		double t = Double.parseDouble(df.format(temperature));
		double tf = Double.parseDouble(df.format(getTemperature(true)));
		int y = year;
		String m = month;
		String c = country;
		String cc = countryCode;
		// Accounts for task C1, in which case the year will be formatted as XXXX-XXXX
		if (y > 2016)
		{
			String dy = y + "";
			String y1 = dy.substring(0,4);
			String y2 = dy.substring(4);
			String altY = y1 + "-" + y2;
			String altFinalString = t + "(C) " + tf + "(F), " + altY + ", " + m + ", " + c + ", " + cc;
			return altFinalString;
		}
		String finalString = t + "(C) " + tf + "(F), " + y + ", " + m + ", " + c + ", " + cc;
		return finalString;
	}
	
	// Implements the compareTo method
	// First sorts by temperature, then country, then year, then month
	public int compareTo(ITemperature o) {
		if (temperature < o.getTemperature(false))
		{
			return -1;
		}
		else if (temperature > o.getTemperature(false))
		{
			return 1;
		}
		else
		{
			if (country.compareTo(o.getCountry()) < 0)
			{
				return -1;
			}
			else if (country.compareTo(o.getCountry()) > 0)
			{
				return 1;
			}
			else
			{
				if (year < o.getYear())
				{
					return -1;
				}
				else if (year > o.getYear())
				{
					return 1;
				}
				else
				{
					if (months.get(month) < months.get(o.getMonth()))
					{
						return -1;
					}
					else if (months.get(month) > months.get(o.getMonth()))
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
			}
		}
	}
}


