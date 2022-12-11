package edu.iit.workload.api;

import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.power.planetlab.PlanetLabRunner;
import edu.iit.workload.service.AlgoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AlgoControllerTest {

    private String baseUrl = "/api/v1";

    @Autowired
    MockMvc mockMvc;

    @Mock
    private PlanetLabRunner planetLabRunner;

    @Mock
    private AlgoService algoService;

    @Test
    void execute_success() throws Exception {
        ExecutableData data = new ExecutableData();
        data.setActive(true);
        data.setParameter("1.5");
        data.setWorkload("20110306");
        data.setVmAllocationPolicy("iqr");
        data.setVmSelectionPolicy("mmt");
        when(this.algoService.getAllExecutableData()).thenReturn(List.of(data));
        when(this.algoService.checkIsExecuted(any())).thenReturn(true);
        doNothing().when(this.planetLabRunner).run(anyBoolean(), anyBoolean(),anyString(),anyString(),any(),anyString());
        mockMvc.perform(get(baseUrl.concat("/execute")).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}
