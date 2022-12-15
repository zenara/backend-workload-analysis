package edu.iit.workload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Chathurika Senani
 */
@EnableAsync
@SpringBootApplication
public class WorkloadAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkloadAnalyzerApplication.class, args);
	}

}
