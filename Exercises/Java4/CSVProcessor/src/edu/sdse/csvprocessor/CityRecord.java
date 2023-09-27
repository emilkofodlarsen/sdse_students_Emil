package edu.sdse.csvprocessor;

public class CityRecord {
	private int id;
	private int year;
	private String cityName;
	private int population;
	
	public String toString() {
		
		return "id: " + id + " year: " + year + " city: " + cityName + " population: " + population;
	}
	
	public CityRecord(int id, int year, String cityName, int population) {
		this.id = id;
		this.year = year;
		this.cityName = cityName;
		this.population = population;
	}
	public String getName() {
		return cityName;
	}
	
	public int getYear() {
		return year;
	}
	public int getPopulation() {
		return population;
	}


}
