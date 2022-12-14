package edu.iit.workload.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.iit.workload.api.dto.ChartResponse;
import edu.iit.workload.domain.ResultAnalysis;
import edu.iit.workload.service.AlgoService;
import edu.iit.workload.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class ChartController {

	private final ChartService chartService;

	private final AlgoService algoService;

	public ChartController(ChartService chartService, AlgoService algoService) {
		this.chartService = chartService;
		this.algoService = algoService;
	}

	@GetMapping("/{type}")
	public ResponseEntity<ChartResponse> chart(@PathVariable String type) throws JsonProcessingException {
		List<Map<String, Object>> data = chartService.chart(type);

		double min = data.stream()
				.map(map -> map.values().stream().filter(a-> !(a instanceof String)).mapToDouble(a -> (double) a).min())
				.mapToDouble(OptionalDouble::getAsDouble)
				.min()
				.getAsDouble();
		double max = data.stream()
				.map(map -> map.values().stream().filter(a-> !(a instanceof String)).mapToDouble(a -> (double) a).max())
				.mapToDouble(OptionalDouble::getAsDouble)
				.max()
				.getAsDouble();

		return ResponseEntity.status(HttpStatus.OK).body(ChartResponse.builder().data(data).min((int) min).max(
				(int) max).build());
	}

	@GetMapping("/chart/summery")
	public ResponseEntity<List<ResultAnalysis>> chartSummery(){
		return ResponseEntity.status(HttpStatus.OK).body(this.algoService.getAnalysedResult());
	}

}
