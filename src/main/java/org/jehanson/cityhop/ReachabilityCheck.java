package org.jehanson.cityhop;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Task that determines whether two cities are connected.
 * <p>
 * Performs breath-first search on a given Roadmap, starting from a source city and searching for a target city.
 */
public class ReachabilityCheck {
	
	private static final String clsName = ReachabilityCheck.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(clsName);
	
	public static enum Result {
		REACHABLE,
		UNREACHABLE,
		ORIGIN_UNKNOWN,
		DESINATION_UNKNOWN
	}
	
	private final Roadmap roadmap;
	private final String originName;
	private final String destinationName;
	private final Set<Roadmap.City> visited;
	
	public ReachabilityCheck(Roadmap roadmap, String originName, String destinationName) {
		this.roadmap = roadmap;
		this.originName = originName;
		this.destinationName = destinationName;
		this.visited = new HashSet<>();
	}
	
	public Result exec() {
		logger.trace("exec: starting. origin=\"{}\" destination=\"{}\"", originName, destinationName);
		Result result = doExec();
		logger.trace("exec: done. result={} visited={}", result, visited.size());
		visited.clear();
		return result;
	}
	
	private Result doExec() {
		Roadmap.City origin = roadmap.getCity(originName);
		if (origin == null)
			return Result.ORIGIN_UNKNOWN;
		
		Roadmap.City destination = roadmap.getCity(destinationName);
		if (destination == null)
			return Result.DESINATION_UNKNOWN;
		
		LinkedList<Roadmap.City> openSet = new LinkedList<>();
		openSet.addLast(origin);
		while (!openSet.isEmpty()) {
			Roadmap.City current = openSet.removeFirst();
			if (current.equals(destination))
				return Result.REACHABLE;
			
			visited.add(current);
			for (String neighborName: current.getNeighbors()) {
				Roadmap.City neighborCity = roadmap.getCity(neighborName);
				// (Assume the roadmap is consistent, i.e., neighborCity exists.)
				if (!visited.contains(neighborCity)) {
					openSet.addLast(neighborCity);
				}				
			}
		}
		
		return Result.UNREACHABLE;
	}

}
