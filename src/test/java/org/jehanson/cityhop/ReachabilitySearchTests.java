package org.jehanson.cityhop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReachabilitySearchTests {

	private Roadmap roadmap;

	public ReachabilitySearchTests() {
		this.roadmap = new Roadmap();
		preload(roadmap);
	}

	@Test
	public void reachability() {
		ReachabilitySearch search = new ReachabilitySearch(roadmap);

		ReachabilitySearch.Result result1 = search.search("A1", "A1");
		assertEquals(result1, ReachabilitySearch.Result.TARGET_REACHABLE);
		
		ReachabilitySearch.Result result2 = search.search("A1", "A2");
		assertEquals(result2, ReachabilitySearch.Result.TARGET_REACHABLE);

		ReachabilitySearch.Result result3 = search.search("A2", "A1");
		assertEquals(result3, ReachabilitySearch.Result.TARGET_REACHABLE);

		ReachabilitySearch.Result result4 = search.search("A1", "A3");
		assertEquals(result4, ReachabilitySearch.Result.TARGET_REACHABLE);

		ReachabilitySearch.Result result5 = search.search("A1", "B1");
		assertEquals(result5, ReachabilitySearch.Result.TARGET_UNREACHABLE);
	}

	@Test
	public void unknownCity() {
		ReachabilitySearch search = new ReachabilitySearch(roadmap);

		ReachabilitySearch.Result result1 = search.search("A1", "C1");
		assertEquals(result1, ReachabilitySearch.Result.TARGET_UNKNOWN);

		ReachabilitySearch.Result result2 = search.search("C1", "A1");
		assertEquals(result2, ReachabilitySearch.Result.SOURCE_UNKNOWN);

	}

	private static void preload(Roadmap roadmap) {
		roadmap.addNeighbors("A1",  "A2");
		roadmap.addNeighbors("A2",  "A3");
		roadmap.addNeighbors("B1",  "B2");
	}
}
