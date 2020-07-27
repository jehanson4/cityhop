package org.jehanson.cityhop;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/** 
 * Task that determines whether two cities are connected.
 * <p>
 * Performs breath-first search on a given Roadmap, starting from a source city and searching for a target city.
 */
public class ReachabilityCheck {
	
	public static enum Result {
		TARGET_REACHABLE,
		TARGET_UNREACHABLE,
		SOURCE_UNKNOWN,
		TARGET_UNKNOWN
	}
	
	private final Roadmap roadmap;
	private final String originName;
	private final String destinationName;
	
	public ReachabilityCheck(Roadmap roadmap, String originName, String destinationName) {
		this.roadmap = roadmap;
		this.originName = originName;
		this.destinationName = destinationName;
	}
	
	public Result exec() {
		Roadmap.City origin = roadmap.getCity(originName);
		if (origin == null)
			return Result.SOURCE_UNKNOWN;
		
		Roadmap.City destination = roadmap.getCity(destinationName);
		if (destination == null)
			return Result.TARGET_UNKNOWN;
		
		Set<Roadmap.City> visited = new HashSet<>();
		LinkedList<Roadmap.City> openSet = new LinkedList<>();
		openSet.addLast(origin);
		
		while (!openSet.isEmpty()) {
			Roadmap.City current = openSet.removeFirst();
			if (current.equals(destination))
				return Result.TARGET_REACHABLE;
			
			visited.add(current);
			for (String neighborName: current.getNeighbors()) {
				// (Assume the roadmap is consistent, i.e., neighborCity exists.)
				Roadmap.City neighborCity = roadmap.getCity(neighborName);
				if (!visited.contains(neighborCity)) {
					openSet.addLast(neighborCity);
				}				
			}
		}
		
		return Result.TARGET_UNREACHABLE;
	}

}
