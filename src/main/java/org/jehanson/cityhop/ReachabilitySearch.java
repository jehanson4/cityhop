package org.jehanson.cityhop;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/** 
 * Determines whether two cities are connected.
 * <p>
 * Performs breath-first search on a given Roadmap, starting from a source city and searching for a target city.
 */
public class ReachabilitySearch {
	
	public static enum Result {
		TARGET_REACHABLE,
		TARGET_UNREACHABLE,
		SOURCE_UNKNOWN,
		TARGET_UNKNOWN
	}
	
	private final Roadmap roadmap;
	
	public ReachabilitySearch(Roadmap roadmap) {
		this.roadmap = roadmap;
	}
	
	public Result search(String sourceName, String targetName) {
		Roadmap.City sourceCity = roadmap.getCity(sourceName);
		if (sourceCity == null)
			return Result.SOURCE_UNKNOWN;
		
		Roadmap.City targetCity = roadmap.getCity(targetName);
		if (targetCity == null)
			return Result.TARGET_UNKNOWN;
		
		Set<Roadmap.City> visited = new HashSet<>();
		LinkedList<Roadmap.City> openSet = new LinkedList<>();
		openSet.addLast(sourceCity);
		
		while (!openSet.isEmpty()) {
			Roadmap.City currentCity = openSet.removeFirst();
			if (currentCity.equals(targetCity))
				return Result.TARGET_REACHABLE;
			
			visited.add(currentCity);
			for (String neighborName: currentCity.getNeighbors()) {
				Roadmap.City neighborCity = roadmap.getCity(neighborName);
				if (!visited.contains(neighborCity)) {
					openSet.addLast(neighborCity);
				}				
			}
		}
		
		return Result.TARGET_UNREACHABLE;
	}

}
