package org.jehanson.cityhop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoadmapTests {

	@Test
	void cityCreation() {
		Roadmap.City cityA = new Roadmap.City("A");
		assertEquals(cityA.getName(), "A");
		assertNotNull(cityA.getNeighbors());
		assertEquals(0, cityA.getNeighbors().size());
	}

	@Test
	public void addNeighbors() {
		Roadmap roadmap = new Roadmap();

		roadmap.addNeighbors("A", "B");
		assertEquals(2, roadmap.getCities().size());

		Roadmap.City cityA = roadmap.getCity("A");
		assertNotNull(cityA);
		assertEquals(1, cityA.getNeighbors().size());
		assertTrue(cityA.getNeighbors().contains("B"));

		Roadmap.City cityB = roadmap.getCity("B");
		assertNotNull(cityB);
		assertEquals(1, cityB.getNeighbors().size());
		assertTrue(cityB.getNeighbors().contains("A"));

		Roadmap.City cityC = roadmap.getCity("C");
		assertNull(cityC);

		roadmap.addNeighbors("A", "C");
		assertEquals(3, roadmap.getCities().size());

		cityA = roadmap.getCity("A"); // re-fetch
		cityC = roadmap.getCity("C");
		assertNotNull(cityC);
		assertEquals(2, cityA.getNeighbors().size());
		assertTrue(cityA.getNeighbors().contains("B"));
		assertTrue(cityA.getNeighbors().contains("C"));
	}

	// @Test
//	public void load() {
//		Roadmap roadmap = new  Roadmap();
//		String url = "goodRoadmap.csv";
//		
//		roadmap.load(url);
//	}

	// @Test
//	public void failedLoad_badURL() {
//		Roadmap roadmap = new  Roadmap();
//		String url = "noSuchRoadmap.csv";
//		
//		roadmap.load(url);
//	}

}
