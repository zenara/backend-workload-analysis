package edu.iit.workload.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.iit.workload.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class ChartController {

	private final ChartService chartService;

	public ChartController(ChartService chartService) {
		this.chartService = chartService;
	}

	@GetMapping("/{type}")
	public List<Map<String, Object>> chart(@PathVariable String type) throws JsonProcessingException {
		return chartService.chart(type);
	}

}
