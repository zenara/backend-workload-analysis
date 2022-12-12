package edu.iit.workload.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ChartResponse {

    private List<Map<String, Object>> data;

    private int max;

    private int min;

}
