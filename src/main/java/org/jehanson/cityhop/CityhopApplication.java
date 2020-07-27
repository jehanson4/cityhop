package org.jehanson.cityhop;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CityhopApplication {

	private static final String clsName = CityhopApplication.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(clsName);
	public static final String defaultFile = "city.txt";
	
	@Autowired
	private Roadmap roadmap;

	public static void main(String[] args) {
		SpringApplication.run(CityhopApplication.class, args);
	}

	@Bean
	CommandLineRunner loadRoadmap() {
		return args -> {
			String fname = (args.length > 0) ? args[0] : defaultFile;
			InputStream is = null;
			try {
				is = new FileInputStream(fname);
				this.roadmap.load(is);
				logger.info("Loaded {} #cities={}", fname, roadmap.getCities().size());
			} finally {
				if (is != null)
					is.close();
			}
		};
	}

}
