package org.jehanson.cityhop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReachabilityEndpoint {

	private static final String clsName = ReachabilityEndpoint.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(clsName);
	
	@Autowired
	private Roadmap roadmap;
	
	@GetMapping("/connected")
	public String connected(
			@RequestParam(value="origin", defaultValue="") String origin,
			@RequestParam(value="destination", defaultValue="") String destination) {		
		ReachabilityCheck check = new ReachabilityCheck(roadmap, origin, destination);
		ReachabilityCheck.Result result = check.exec();
		logger.debug("connected: done. origin=\"{}\", destination=\"{}\", result={}", origin, destination, result);
		return (result == ReachabilityCheck.Result.REACHABLE) ? "yes" : "no";
	}
	

}
