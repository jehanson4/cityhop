package org.jehanson.cityhop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityhopApplicationTests {

	@Autowired
	private Roadmap roadmap;

	@Test
	void contextLoads() {
		assertNotNull(roadmap);
	}

}
