package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityCSVProcessor {
	
	List<CityRecord> cities = new ArrayList<CityRecord>();
	
	Map<String, List<CityRecord>> citiesByName = new HashMap<String, List<CityRecord>>();
	
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				CityRecord cityObject = new CityRecord(id,year,city,population);
				
				//System.out.println("id: " + id + ", year: " + year + ", city: " + city + ", population: " + population);
				//System.out.println(cityObject);
				cities.add(cityObject);
			}
			//TODO:analyze data here
			System.out.println(cities);
			processData();
			
			
			
			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
	
	private void processData() {
		for (CityRecord city : cities) {
			String key = city.getName();
			//TODO: check if key is already set and if get the list and append and reset the value with the update list
			if (citiesByName.containsKey(key)) {
				List<CityRecord> existingList = citiesByName.get(key);
				
				existingList.add(city);
				citiesByName.put(key, existingList);
				
			} else {
				List<CityRecord> newList = new ArrayList<CityRecord>();
				newList.add(city);
				citiesByName.put(key, newList);
			}
		}
		System.out.println(citiesByName);
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}
