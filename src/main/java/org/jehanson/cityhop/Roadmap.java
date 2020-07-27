package org.jehanson.cityhop;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * Directed graph of cities and roads.
 * <p>
 * Roads are not explicitly modeled. Instead, each City maintains a set of its neighboring Cities.
 * 
 */
@Service
public class Roadmap {

	public static class City {
		
		private final String name;
		
		/** Names of cities directly connected to this one */
		private final Set<String> neighbors;
		
		public City(String name) {
			if (name == null)
				throw new IllegalArgumentException("name cannot be null");
			this.name = name;
			this.neighbors = new HashSet<>();
		}
		
		public String getName() {
			return name;
		}
		
		public Set<String> getNeighbors() {
			return Collections.unmodifiableSet(neighbors);
		}
		
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (!(obj instanceof City)) {
				return false;
			}
			return this.name.equals(((City)obj).name);
		}
		
		public int hashCode() {
			return name.hashCode();
		}
		
	}
	
	/** key = name of city. */
	private final Map<String, City> cities;
	
	public Roadmap() {
		cities = new HashMap<>();
	}
	
	public City getCity(String name) {
		return cities.get(name);
	}
	
	public Collection<City> getCities() {
		return Collections.unmodifiableCollection(cities.values());
		
	}
	
	/** 
	 * Adds two cities and a two-way road between them. OK if they already exist.
	 * 
	 *  @param name1 Name of a city. Not null.
	 *  @param name2 Name of another city. Not null, not equal to name1.
	 */
	public void addNeighbors(String name1, String name2) {
		City city1 = cities.get(name1);
		if (city1 == null) {
			city1 = new City(name1);
			cities.put(name1,  city1);
		}
		
		City city2 = cities.get(name2);
		if (city2 == null) {
			city2 = new City(name2);
			cities.put(name2,  city2);
		}
		
		city1.neighbors.add(name2);
		city2.neighbors.add(name1);
	}
	
	public void load(String url) {
		// TODO
	}
	

}
