package org.jehanson.cityhop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

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

	@Test
	public void load() throws IOException, FormatException {
		Roadmap roadmap = new Roadmap();
		InputStream is = this.getClass().getResourceAsStream("goodCity.txt");
		try {
			roadmap.load(is);
		} finally {
			is.close();
		}
	}


	@Test
	public void failedLoad() throws IOException {
		Roadmap roadmap = new Roadmap();
		InputStream is = this.getClass().getResourceAsStream("badCity.txt");
		try {
			assertThrows(FormatException.class, () -> { roadmap.load(is); });
		} finally {
			is.close();
		}
	}


}
