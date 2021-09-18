package climateChange;

import java.util.*;
import java.io.*;

public class WeatherIO implements IWeatherIO 
{
	
	private File currentFile = null;

	// Reads the .csv file and transfers its contents into an ITemperature ArrayList
	public ArrayList<ITemperature> readDataFromFile(String fileName) 
	{
		try
		{
			ArrayList<ITemperature> weatherData = new ArrayList<ITemperature>();
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			br.readLine(); // Gets past the first line, which has no data
			String line = br.readLine();
			while (line != null)
			{
				String[] data = line.split(", ");
				// sets variables for each index of the array
				double temp = Double.parseDouble(data[0]);
				int year = Integer.parseInt(data[1]);
				String month = data[2];
				String country;
				// Accounts for countries which have commas in the name
				if (data.length > 5)
				{
					country = data[4] + " " + data[3];
				}
				else
				{
					country = data[3];
				}
				String countryCode = data[data.length - 1];
				ITemperature temperature = new Temperature(temp, year, month, country, countryCode);
				weatherData.add(temperature);
				line = br.readLine();
				
			}
			br.close();
			fr.close();
			return weatherData;
		}
		catch (FileNotFoundException f)
		{
			System.out.println("There is no file with the name: " + fileName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Creates a subject header that tells the user what the file is displaying
	public void writeSubjectHeaderInFile(String filename, String subject) 
	{
		String header = "Temperature, Year, Month_Avg, Country, Country_Code";
		try
		{
			currentFile = new File("data/" + filename);
			// Will not append if the file already exists
			if (!currentFile.exists())
			{
				PrintWriter pw = new PrintWriter(currentFile);
				pw.println(subject);
				pw.println(header);
				pw.close();
			}
			// Appends if the file already exists
			else
			{
				File fileName = currentFile;
				FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				pw.println();
				pw.println(subject);
				pw.println(header);
				pw.close();
				bw.close();
				fw.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Writes all the data into the new file
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList)
	{
		writeSubjectHeaderInFile(filename, topic);
		// Appends all the data into the file
		try
		{
			File fileName = currentFile;
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			for (ITemperature i : theWeatherList)
			{
				pw.println(i);
			}
			pw.close();
			bw.close();
			fw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
