package edu.iit.workload.service;

import edu.iit.workload.domain.ResultAnalysis;
import edu.iit.workload.repository.ResultDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChartService {

	private final ResultDataRepository resultDataRepository;

	public ChartService(ResultDataRepository resultDataRepository) {
		this.resultDataRepository = resultDataRepository;
	}

	public List<Map<String, Object>> chart(String type) {

		List<ResultAnalysis> entities = resultDataRepository.findAll();

		List<Map<String, Object>> result = entities.stream().map(ResultAnalysis::getVmSelectionPolicy).distinct()
				.map(m -> {
					Map<String, Object> map = entities.stream().filter(r -> r.getVmSelectionPolicy().equals(m)).collect(
							Collectors.toMap(a -> a.getVmAllocationPolicy().toUpperCase(), a -> findValue(type, a)));
					map.put("name", m.toUpperCase());
					return map;
				}).toList();

		return result;

	}

	private double findValue(String type, ResultAnalysis resultAnalysis) {
		if ("energy".equals(type)) {
			return resultAnalysis.getEnergy();
		}
		return 0;
	}

}
