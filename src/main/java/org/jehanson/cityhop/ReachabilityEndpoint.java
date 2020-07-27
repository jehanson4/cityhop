package org.jehanson.cityhop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReachabilityEndpoint {

	@Autowired
	private Roadmap roadmap;
	
	@GetMapping("/connected")
	public String connected(
			@RequestParam(value="origin", defaultValue="") String origin,
			@RequestParam(value="destination", defaultValue="") String destination) {
		
		ReachabilityCheck check = new ReachabilityCheck(roadmap, origin, destination);
		try {
			System.out.println("origin=" + origin);
			System.out.println("destination=" + destination);
			ReachabilityCheck.Result result = check.exec();
			System.out.println("result=" + result);
			return (result == ReachabilityCheck.Result.TARGET_REACHABLE) ? "yes" : "no";
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

}
