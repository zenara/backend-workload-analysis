package edu.iit.workload.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class ChartController {

    @GetMapping("/{type}")
    public JsonNode chart(@PathVariable String type) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue("[\n" +
                "   {\n" +
                "      \"name\":\"MC\",\n" +
                "      \"IQR\":4000,\n" +
                "      \"LR\":2400,\n" +
                "      \"MAD\":1400,\n" +
                "      \"LRR\":400,\n" +
                "      \"THR\":300,\n" +
                "      \"GA\":100\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"MMT\",\n" +
                "      \"IQR\":3000,\n" +
                "      \"LR\":1398,\n" +
                "      \"MAD\":1698,\n" +
                "      \"LRR\":5398,\n" +
                "      \"THR\":6398,\n" +
                "      \"GA\":210\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"MU\",\n" +
                "      \"IQR\":2000,\n" +
                "      \"LR\":9800,\n" +
                "      \"MAD\":7800,\n" +
                "      \"LRR\":9800,\n" +
                "      \"THR\":3000,\n" +
                "      \"GA\":900\n" +
                "   },\n" +
                "   {\n" +
                "      \"name\":\"RS\",\n" +
                "      \"IQR\":2780,\n" +
                "      \"LR\":3908,\n" +
                "      \"MAD\":708,\n" +
                "      \"LRR\":1908,\n" +
                "      \"THR\":908,\n" +
                "      \"GA\":70\n" +
                "   }\n" +
                "]", JsonNode.class);
        return node;

    }

}
