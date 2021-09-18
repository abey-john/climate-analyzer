package climateChange;

import java.util.*;
import java.io.*;

// Main method is in this class

public class ClimateAnalyzer implements IClimateAnalyzer {
	
	private ArrayList<ITemperature> weatherData;
	private ArrayList<String> countryData;
	private HashMap<String, Integer> months;
	private HashMap<Integer, String> reverseMonths;
	
	// Constructor for the ClimateAnalyzer class
	// Initializes all the instance variables
	public ClimateAnalyzer()
	{
		WeatherIO wio = new WeatherIO();
		// Takes in the file that will be analyzed
		weatherData = wio.readDataFromFile("data/world_temp_2000-2016.csv");
		
		countryData = new ArrayList<String>();
		months = new HashMap<String, Integer>();
		reverseMonths = new HashMap<Integer, String>();
		// Creates a hashmap to map months to numbers
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
		// Creates a hashmap to map numbers to months
		reverseMonths.put(1, "January");
		reverseMonths.put(2, "February");
		reverseMonths.put(3, "March");
		reverseMonths.put(4, "April");
		reverseMonths.put(5, "May");
		reverseMonths.put(6, "June");
		reverseMonths.put(7, "July");
		reverseMonths.put(8, "August");
		reverseMonths.put(9, "September");
		reverseMonths.put(10, "October");
		reverseMonths.put(11, "November");
		reverseMonths.put(12, "December");
		
		// Gets a list of all the countries
		for (ITemperature i : weatherData)
		{
			if (!countryData.contains(i.getCountry()))
			{
				countryData.add(i.getCountry());
			}
		}
		
	}

	// TASK A1.1
	// Finds the lowest temperature with a specified country and month
	public ITemperature getLowestTempByMonth(String country, int month) 
	{
		ITemperature lowestTemp = null;
		double minTemp = Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			int iMonth = months.get(i.getMonth());
			if (iCountry.equals(country) && iMonth == month)
			{
				if (i.getTemperature(false) < minTemp)
				{
					lowestTemp = i;
					minTemp = i.getTemperature(false);
				}
			}
		}
		return lowestTemp;
	}

	// TASK A1.2
	// Finds the highest temperature with a specified country and month
	public ITemperature getHighestTempByMonth(String country, int month)
	{	
		ITemperature highestTemp = null;
		double maxTemp = -1 * Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			int iMonth = months.get(i.getMonth());
			if (iCountry.equals(country) && iMonth == month)
			{
				if (i.getTemperature(false) > maxTemp)
				{
					highestTemp = i;
					maxTemp = i.getTemperature(false);
				}
			}
		}
		return highestTemp;
	}

	// TASK A2.1
	// Finds the lowest temperature with a specified country and year
	public ITemperature getLowestTempByYear(String country, int year) 
	{	
		ITemperature lowestTemp = null;
		double minTemp = Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			int iYear = i.getYear();
			if (iCountry.equals(country) && iYear == year)
			{
				if (i.getTemperature(false) < minTemp)
				{
					lowestTemp = i;
					minTemp = i.getTemperature(false);
				}
			}
		}
		return lowestTemp;
	}

	// TASK A2.2
	// Finds the highest temperature with a specified country and year
	public ITemperature getHighestTempByYear(String country, int year)
	{	
		ITemperature highestTemp = null;
		double maxTemp = -1 * Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			int iYear = i.getYear();
			if (iCountry.equals(country) && iYear == year)
			{
				if (i.getTemperature(false) > maxTemp)
				{
					highestTemp = i;
					maxTemp = i.getTemperature(false);
				}
			}
		}
		return highestTemp;
	}
	
	// TASK A3.1
	// Returns a sorted set of all the temperatures of a specified country within a certain range
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp)
	{	
		TreeSet<ITemperature> temperatureSet = new TreeSet<ITemperature>();
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			double iTemperature = i.getTemperature(false);
			if (iCountry.contentEquals(country))
			{
				if (rangeLowTemp <= iTemperature && iTemperature <= rangeHighTemp)
				{
					temperatureSet.add(i);
				}
			}
		}
		return temperatureSet;
	}

	// TASK A4.1
	// Finds the lowest temperature recording of a specified country
	public ITemperature getLowestTempYearByCountry(String country) 
	{	
		ITemperature lowestTemp = null;
		double minTemp = Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			if (iCountry.equals(country))
			{
				if (i.getTemperature(false) < minTemp)
				{
					lowestTemp = i;
					minTemp = i.getTemperature(false);
				}
			}
		}
		return lowestTemp;
	}

	// TASK A4.2
	// Finds the highest temperature recording of a specified country
	public ITemperature getHighestTempYearByCountry(String country)
	{	
		ITemperature highestTemp = null;
		double maxTemp = -1 * Double.MAX_VALUE;
		for (ITemperature i : weatherData)
		{
			String iCountry = i.getCountry();
			if (iCountry.equals(country))
			{
				if (i.getTemperature(false) > maxTemp)
				{
					highestTemp = i;
					maxTemp = i.getTemperature(false);
				}
			}
		}
		return highestTemp;
	}

	// TASK B1.1
	// Finds the 10 countries with the lowest temperatures in the world for a specified month
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) 
	{	
		ArrayList<ITemperature> top10LowestTemps = new ArrayList<ITemperature>();
		ArrayList<String> countryList = new ArrayList<String>();
		double highestLowTemp = -1 * Double.MAX_VALUE;
		for (int j = 0; j < 10; j++)
		{
			ITemperature lowestTemp = null;
			double minTemp = Double.MAX_VALUE;
			for (ITemperature i : weatherData)
			{
				int iMonth = months.get(i.getMonth());
				if (iMonth == month)
				{
					if (!countryList.contains(i.getCountry()))
					{
						if (i.getTemperature(false) < minTemp && i.getTemperature(false) > highestLowTemp)
						{
							lowestTemp = i;
							minTemp = i.getTemperature(false);
						}
					}
				}
			}
			highestLowTemp = lowestTemp.getTemperature(false);
			top10LowestTemps.add(lowestTemp);
			countryList.add(lowestTemp.getCountry());
		}
		return top10LowestTemps;
	}

	// TASK B1.2
	// Finds the 10 countries with the highest temperatures in the world for a specified month
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) 
	{	
		ArrayList<ITemperature> top10HighestTemps = new ArrayList<ITemperature>();
		ArrayList<String> countryList = new ArrayList<String>();
		double lowestHighTemp = Double.MAX_VALUE;
		for (int j = 0; j < 10; j++)
		{
			ITemperature highestTemp = null;
			double maxTemp = -1 * Double.MIN_VALUE;
			for (ITemperature i : weatherData)
			{
				int iMonth = months.get(i.getMonth());
				if (iMonth == month)
				{
					if (!countryList.contains(i.getCountry()))
					{
						if (i.getTemperature(false) > maxTemp && i.getTemperature(false) < lowestHighTemp)
						{
							highestTemp = i;
							maxTemp = i.getTemperature(false);
						}
					}
				}
			}
			lowestHighTemp = highestTemp.getTemperature(false);
			top10HighestTemps.add(highestTemp);
			countryList.add(highestTemp.getCountry());
		}
		Collections.reverse(top10HighestTemps);
		return top10HighestTemps;
	}

	// TASK B2.1
	// Finds the 10 countries with the lowest temperatures in the world
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp() 
	{	
		ArrayList<ITemperature> top10LowestTemps = new ArrayList<ITemperature>();
		ArrayList<String> countryList = new ArrayList<String>();
		double highestLowTemp = -1 * Double.MAX_VALUE;
		for (int j = 0; j < 10; j++)
		{
			ITemperature lowestTemp = null;
			double minTemp = Double.MAX_VALUE;
			for (ITemperature i : weatherData)
			{
				if (!countryList.contains(i.getCountry()))
				{
					if (i.getTemperature(false) < minTemp && i.getTemperature(false) > highestLowTemp)
					{
						lowestTemp = i;
						minTemp = i.getTemperature(false);
					
					}
				}
			}
			highestLowTemp = lowestTemp.getTemperature(false);
			top10LowestTemps.add(lowestTemp);
			countryList.add(lowestTemp.getCountry());
		}
		return top10LowestTemps;
	}

	// TASK B2.2
	// Finds the 10 countries with 
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp() 
	{		
		ArrayList<ITemperature> top10HighestTemps = new ArrayList<ITemperature>();
		ArrayList<String> countryList = new ArrayList<String>();
		double lowestHighTemp = Double.MAX_VALUE;
		for (int j = 0; j < 10; j++)
		{
			ITemperature highestTemp = null;
			double maxTemp = -1 * Double.MIN_VALUE;
			for (ITemperature i : weatherData)
			{
				if (!countryList.contains(i.getCountry()))
				{
					if (i.getTemperature(false) > maxTemp && i.getTemperature(false) < lowestHighTemp)
					{
						highestTemp = i;
						maxTemp = i.getTemperature(false);
					}
				}
			}
			lowestHighTemp = highestTemp.getTemperature(false);
			top10HighestTemps.add(highestTemp);
			countryList.add(highestTemp.getCountry());
		}
		Collections.reverse(top10HighestTemps);
		return top10HighestTemps;
	}

	// TASK B3.1
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp) 
	{	
		TreeSet<ITemperature> temperatureSet = new TreeSet<ITemperature>();
		ArrayList<ITemperature> temperatureList = new ArrayList<ITemperature>();
		for (ITemperature i : weatherData)
		{
			double iTemperature = i.getTemperature(false);
			if (lowRangeTemp <= iTemperature && iTemperature <= highRangeTemp)
			{
				temperatureSet.add(i);
			}
		}
		for (ITemperature i : temperatureSet)
		{
			temperatureList.add(i);
		}
		return temperatureList;
	}

	// TASK C1.1
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2) 
	{
		TreeSet<String> countryList = new TreeSet<String>();
		ArrayList<ITemperature> year2Temps = new ArrayList<ITemperature>();
		ArrayList<ITemperature> year1Temps = new ArrayList<ITemperature>();
		TreeSet<ITemperature> tempDeltas = new TreeSet<ITemperature>();
		ArrayList<ITemperature> copyTempDeltas = new ArrayList<ITemperature>();
		ArrayList<ITemperature> top10TempDeltas = new ArrayList<ITemperature>();
		for (ITemperature i : weatherData)
		{
			countryList.add(i.getCountry());
		}
		for (String country : countryList)
		{
			ITemperature year1Temp = findSpecificTemperature(month, year1, country);
			ITemperature year2Temp = findSpecificTemperature(month, year2, country);
			year1Temps.add(year1Temp);
			year2Temps.add(year2Temp);
		}
		for (int i = 0; i < year2Temps.size(); i++)
		{
			double deltaTemp = Math.abs((year2Temps.get(i).getTemperature(false) - year1Temps.get(i).getTemperature(false)));
			String dYear = year1Temps.get(i).getYear() + "" + year2Temps.get(i).getYear();
			int deltaYear = Integer.parseInt(dYear);
			String deltaMonth = year2Temps.get(i).getMonth();
			String deltaCountry = year2Temps.get(i).getCountry();
			String deltaCountryCode = year2Temps.get(i).getCountry3LetterCode();
			ITemperature delta = new Temperature(deltaTemp, deltaYear, deltaMonth, deltaCountry, deltaCountryCode);
			tempDeltas.add(delta);
		}
		for (ITemperature i : tempDeltas)
		{
			copyTempDeltas.add(i);
		}
		for (int i = copyTempDeltas.size() - 11; i < copyTempDeltas.size(); i++)
		{
			top10TempDeltas.add(copyTempDeltas.get(i));
		}
		return top10TempDeltas;
	}
	
	// Helper Method
	// Finds a specific temperature data based on month, year, and country
	public ITemperature findSpecificTemperature(int month, int year, String country)
	{
		ITemperature specificTemp = null;
		for (ITemperature i : weatherData)
		{
			int iMonth = months.get(i.getMonth());
			int iYear = i.getYear();
			String iCountry = i.getCountry();
			if (iMonth == month && iYear == year && iCountry.equals(country))
			{
				specificTemp = i;
			}
		}
		return specificTemp;
	}
	
	// Helper Method
	// Deals with user input for months
	// Handles invalid input
	public int inputMonth(Scanner input)
	{
		int month = 0;
		try
		{
			month = input.nextInt();
			input.nextLine();
			if (month < 1 || month > 12)
			{
				System.out.print("Please enter a valid number from 1-12: ");
				month = inputMonth(input);
			}
			
		}
		catch (InputMismatchException e)
		{
			System.out.print("Please enter a valid number from 1-12: ");
			input.next();
			month = inputMonth(input);
		}
		return month;
	}
	
	// Helper Method
	// Deals with user input for years
	// Handles invalid input
	public int inputYear(Scanner input)
	{
		int year = 0;
		try
		{
			year = input.nextInt();
			input.nextLine();
			if (year < 2000 || year > 2016)
			{
				System.out.print("Please enter a valid year from 2000-2016: ");
				year = inputYear(input);
			}
			
		}
		catch (InputMismatchException e)
		{
			System.out.print("Please enter a valid year from 2000-2016: ");
			input.next();
			year = inputYear(input);
		}
		return year;
	}
	
	// Helper Method
	// Deals with user input for temperatures
	// Handles invalid input
	public double inputTemp(Scanner input)
	{
		double temp = 0;
		try
		{
			temp = input.nextDouble();
			input.nextLine();
		}
		catch (InputMismatchException e)
		{
			System.out.print("Please enter a valid temperature: ");
			input.next();
			temp = inputTemp(input);
		}
		return temp;
	}
	
	// Helper Method
	// Deals with user input for countries
	// Handles invalid input
	public String inputCountry(Scanner input)
	{
		String country = "";
		try
		{
			country = input.nextLine();
			if (!countryData.contains(country))
			{
				System.out.print("Please enter a valid country name: ");
				country = inputCountry(input);
			}
			
		}
		catch (InputMismatchException e)
		{
			System.out.print("Please enter a valid country name: ");
			input.next();
			country = inputCountry(input);
		}
		return country;
	}

		
	// Takes in all the user input
	// Executes all the methods and writes the data to files
	public void runClimateAnalyzer() 
	{
		
		WeatherIO wio = new WeatherIO();
		Scanner input = new Scanner(System.in);
		
		// Deletes all files whose names start with "task" when the program is run
		// This is to prevent it from appending the data to already existing files
		File dir = new File("data");
		File[] files = dir.listFiles();
		for (File f : files)
		{
			if (f.getName().substring(0,4).equals("task"))
			{
				f.delete();
			}
		}
		
		
		System.out.println("Please enter values according to the instructions prompted.");
		System.out.println("The desired information will be available in the \"data\" folder.");
		System.out.println();
		
		
		// TASK A1
		System.out.println("TASK A1");
		System.out.println("This will get the lowest temperature in any given month and country");
		System.out.print("Enter a country: ");
		String a11Country = inputCountry(input);
		System.out.print("Enter a number 1-12 for a month: ");
		int a11Month = inputMonth(input);
		ITemperature a11 = getLowestTempByMonth(a11Country, a11Month);
		ArrayList<ITemperature> a11List = new ArrayList<ITemperature>();
		a11List.add(a11);
		String a11subject = "Lowest Temperature in " + reverseMonths.get(a11Month) + " in " + a11Country;
		wio.writeDataToFile("taskA1_climate_info.csv", a11subject, a11List);
		
		System.out.println("This will get the highest temperature in any given month and country");
		System.out.print("Enter a country: ");
		String a12Country = inputCountry(input);
		System.out.print("Enter a number 1-12 for a month: ");
		int a12Month = inputMonth(input);
		ITemperature a12 = getHighestTempByMonth(a12Country, a12Month);
		ArrayList<ITemperature> a12List = new ArrayList<ITemperature>();
		a12List.add(a12);
		String a12subject = "Highest Temperature in " + reverseMonths.get(a12Month) + " in " + a12Country;
		wio.writeDataToFile("taskA1_climate_info.csv", a12subject, a12List);
		
		// TASK A2
		System.out.println();
		System.out.println("TASK A2");
		System.out.println("This will get the lowest temperature in any given year and country");
		System.out.print("Enter a country: ");
		String a21Country = inputCountry(input);
		System.out.print("Enter a year from 2000-2016: ");
		int a21Year = inputYear(input);
		ITemperature a21 = getLowestTempByYear(a21Country, a21Year);
		ArrayList<ITemperature> a21List = new ArrayList<ITemperature>();
		a21List.add(a21);
		String a21subject = "Lowest Temperature in " + a21Country + " in " + a21Year;
		wio.writeDataToFile("taskA2_climate_info.csv", a21subject, a21List);
		
		System.out.println("This will get the highest temperature in any given year and country");
		System.out.print("Enter a country: ");
		String a22Country = inputCountry(input);
		System.out.print("Enter a year from 2000-2016: ");
		int a22Year = inputYear(input);
		ITemperature a22 = getHighestTempByYear(a22Country, a22Year);
		ArrayList<ITemperature> a22List = new ArrayList<ITemperature>();
		a22List.add(a22);
		String a22subject = "Highest Temperature in " + a22Country + " in " + a22Year;
		wio.writeDataToFile("taskA2_climate_info.csv", a22subject, a22List);
		
		// TASK A3
		System.out.println();
		System.out.println("TASK A3");
		System.out.println("This will get every temperature within a specified range for any given country");
		System.out.print("Enter a country: ");
		String a31Country = inputCountry(input);
		System.out.print("Enter a lower temperature range in celsius: ");
		double a31LowRange = inputTemp(input);
		System.out.print("Enter an upper temperature range in celsius: ");
		double a31HighRange = inputTemp(input);
		TreeSet<ITemperature> a31TreeSet = getTempWithinRange(a31Country, a31LowRange, a31HighRange);
		ArrayList<ITemperature> a31List = new ArrayList<ITemperature>();
		for (ITemperature i : a31TreeSet)
		{
			a31List.add(i);
		}
		String a31subject = "All Temperatures Between " + a31LowRange + " and " + a31HighRange + " in " + a31Country;
		wio.writeDataToFile("taskA3_climate_info.csv", a31subject , a31List);
		
		// TASK A4
		System.out.println();
		System.out.println("TASK A4");
		System.out.println("This will get the lowest temperature reading in any given country");
		System.out.print("Enter a country: ");
		String a41Country = inputCountry(input);
		ITemperature a41 = getLowestTempYearByCountry(a41Country);
		ArrayList<ITemperature> a41List = new ArrayList<ITemperature>();
		a41List.add(a41);
		String a41subject = "Lowest Temperature Recorded in " + a41Country;
		wio.writeDataToFile("taskA4_climate_info.csv", a41subject, a41List);
		
		System.out.println("This will get the highest temperature reading in any given country");
		System.out.print("Enter a country: ");
		String a42Country = inputCountry(input);
		ITemperature a42 = getHighestTempYearByCountry(a42Country);
		ArrayList<ITemperature> a42List = new ArrayList<ITemperature>();
		a42List.add(a42);
		String a42subject = "Highest Temperature Recorded in " + a42Country;
		wio.writeDataToFile("taskA4_climate_info.csv", a42subject, a42List);
		
		// TASK B1
		System.out.println();
		System.out.println("TASK B1");
		System.out.println("This will get the 10 countries with the lowest temperature readings in the world for a given month");
		System.out.print("Enter a number 1-12 for a month: ");
		int b11Month = inputMonth(input);
		ArrayList<ITemperature> b11List = allCountriesGetTop10LowestTemp(b11Month);
		String b11subject = "10 Countries with the Lowest Temperatures in the World in " + reverseMonths.get(b11Month);
		wio.writeDataToFile("taskB1_climate_info.csv", b11subject, b11List);
		
		System.out.println("This will get the 10 countries with the highest temperature readings in the world for a given month");
		System.out.print("Enter a number 1-12 for a month: ");
		int b12Month = inputMonth(input);
		ArrayList<ITemperature> b12List = allCountriesGetTop10HighestTemp(b12Month);
		String b12subject = "10 Countries with the Highest Temperatures in the World in " + reverseMonths.get(b12Month);
		wio.writeDataToFile("taskB1_climate_info.csv", b12subject, b12List);
		
		// TASK B2
		System.out.println();
		System.out.println("TASK B2");
		System.out.println("This will get the 10 countries with the lowest temperature readings in the world");
		ArrayList<ITemperature> b21List = allCountriesGetTop10LowestTemp();
		wio.writeDataToFile("taskB2_climate_info.csv", "10 Countries with the Lowest Temperatures in the World", b21List);
		
		System.out.println("This will get the 10 countries with the highest temperature readings in the world for a given month");
		ArrayList<ITemperature> b22List = allCountriesGetTop10HighestTemp();
		wio.writeDataToFile("taskB2_climate_info.csv", "10 Countries with the Highest Temperatures in the World", b22List);
		
		// TASK B3
		System.out.println();
		System.out.println("TASK B3");
		System.out.println("This will get all the temperature readings in the world within a specified range");
		System.out.print("Enter a lower temperature range in celsius: ");
		double b31LowRange = inputTemp(input);
		System.out.print("Enter an upper temperature range in celsius: ");
		double b31HighRange = inputTemp(input);
		ArrayList<ITemperature> b31List = allCountriesGetAllDataWithinTempRange(b31LowRange, b31HighRange);
		String b31subject = "All Temperatures (Celsius) Between " + b31LowRange + " and " + b31HighRange + " in the World";
		wio.writeDataToFile("taskB3_climate_info.csv", b31subject, b31List);
		
		// TASK C1
		System.out.println();
		System.out.println("TASK C1");
		System.out.println("This will get the 10 countries whose temperatures for the same month have changed the most from one year to another.");
		System.out.print("Enter a number 1-12 for a month: ");
		int c11Month = inputMonth(input);
		System.out.print("Enter a start year from 2000-2016: ");
		int c11StartYear = inputYear(input);
		System.out.print("Enter an end year from 2000-2016: ");
		int c11EndYear = inputYear(input);
		ArrayList<ITemperature> c11List = allCountriesTop10TempDelta(c11Month, c11StartYear, c11EndYear);
		String c11subject = "10 Countries Whose Temperatures for " + reverseMonths.get(c11Month) + 
							" have Changed the Most from " + c11StartYear + " to " + c11EndYear;
		wio.writeDataToFile("taskC1_climate_info.csv", c11subject, c11List);
		
		System.out.println();
		System.out.println("All the information you entered is now available in the .csv files in the \"data\" folder");
		
		input.close();
	}
	
	// Runs the class
	public static void main(String[] args)
	{
		ClimateAnalyzer climateAnalyzer = new ClimateAnalyzer();
		climateAnalyzer.runClimateAnalyzer();
	}
}
