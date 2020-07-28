package org.jehanson.cityhop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReachabilityTests {

	private Roadmap roadmap;

	public ReachabilityTests() {
		this.roadmap = new Roadmap();
		preload(roadmap);
	}

	@Test
	public void targetIsSource() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A1");
		assertEquals(task.exec(), ReachabilityCheck.Result.REACHABLE);		
	}
	
	
	@Test
	public void targetIsOneHop() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A2");
		assertEquals(task.exec(), ReachabilityCheck.Result.REACHABLE);		
	}
	
	@Test
	public void targetIsMultipleHops() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A3");
		assertEquals(task.exec(), ReachabilityCheck.Result.REACHABLE);
	}
	
	@Test
	public void targetIsUnreachable() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "B1");
		assertEquals(task.exec(), ReachabilityCheck.Result.UNREACHABLE);		
	}
	
	@Test
	public void sourceIsUnknown() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "C1", "A1");
		assertEquals(task.exec(), ReachabilityCheck.Result.ORIGIN_UNKNOWN);		
	}
	
	@Test
	public void targetIsUnknown() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "C1");
		assertEquals(task.exec(), ReachabilityCheck.Result.DESINATION_UNKNOWN);		
	}

	private static void preload(Roadmap roadmap) {
		roadmap.addNeighbors("A1",  "A2");
		roadmap.addNeighbors("A2",  "A3");
		roadmap.addNeighbors("B1",  "B2");
	}
}
