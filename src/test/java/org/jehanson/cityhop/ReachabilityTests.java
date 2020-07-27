package org.jehanson.cityhop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReachabilityTests {

	private Roadmap roadmap;

	public ReachabilityTests() {
		this.roadmap = new Roadmap();
		preload(roadmap);
	}

	@Test
	public void targetIsSource() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A1");
		assertEquals(task.exec(), ReachabilityCheck.Result.TARGET_REACHABLE);		
	}
	
	
	@Test
	public void targetIsOneHop() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A2");
		assertEquals(task.exec(), ReachabilityCheck.Result.TARGET_REACHABLE);		
	}
	
	@Test
	public void targetIsMultipleHops() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "A3");
		assertEquals(task.exec(), ReachabilityCheck.Result.TARGET_REACHABLE);
	}
	
	@Test
	public void targetIsUnreachable() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "B1");
		assertEquals(task.exec(), ReachabilityCheck.Result.TARGET_UNREACHABLE);		
	}
	
	@Test
	public void sourceIsUnknown() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "C1", "A1");
		assertEquals(task.exec(), ReachabilityCheck.Result.SOURCE_UNKNOWN);		
	}
	
	@Test
	public void targetIsUnknown() {
		ReachabilityCheck task = new ReachabilityCheck(roadmap, "A1", "C1");
		assertEquals(task.exec(), ReachabilityCheck.Result.TARGET_UNKNOWN);		
	}

	private static void preload(Roadmap roadmap) {
		roadmap.addNeighbors("A1",  "A2");
		roadmap.addNeighbors("A2",  "A3");
		roadmap.addNeighbors("B1",  "B2");
	}
}
