package org.jehanson.cityhop;

public class ReachabilityQuery {

	private final String origin;
	private final String destination;
	
	public ReachabilityQuery(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}
	
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	
}
