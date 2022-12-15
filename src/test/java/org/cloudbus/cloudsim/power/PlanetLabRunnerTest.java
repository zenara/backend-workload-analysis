package org.cloudbus.cloudsim.power;

import edu.iit.workload.DemoApplication;
import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.domain.ResultAnalysis;
import edu.iit.workload.power.Helper;
import edu.iit.workload.power.planetlab.PlanetLabRunner;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.core.CloudSim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
public class PlanetLabRunnerTest {

    @InjectMocks
    private PlanetLabRunner planetLabRunner;

    @Value("${folder.output}")
    private String outputFolder;

    @Value("${folder.input}")
    private String inputFolder;

    private ExecutableData executableData;

    @Mock
    private Helper helper;

    private ResultAnalysis resultAnalysis;

    @BeforeEach
    void before(){
        executableData = new ExecutableData();
        executableData.setId(1L);
        executableData.setVmSelectionPolicy("mu");
        executableData.setVmAllocationPolicy("ga");
        executableData.setWorkload("20110306");
        executableData.setActive(true);

        resultAnalysis = new ResultAnalysis();
        resultAnalysis.setEnergy(0.00092D);
    }

    @Test
    void start_success(){
        VmAllocationPolicy vmAllocationPolicy = planetLabRunner.getVmAllocationPolicy("ga", "mu",
                "1.5");

        try (MockedStatic<CloudSim> mocked =  Mockito.mockStatic(CloudSim.class)){
            mocked.when(CloudSim::startSimulation).thenReturn(1D);

            when(helper.printResults(any(), anyList(), anyDouble(), anyString(), anyBoolean(), anyString(),
                    any(), anyString())).thenReturn(resultAnalysis);

            planetLabRunner.init(inputFolder + "/" + executableData.getWorkload());
            ResultAnalysis start = planetLabRunner.start("20110306_ga_mu_1.5", outputFolder, vmAllocationPolicy,
                    executableData, UUID.randomUUID().toString());
            assertEquals(resultAnalysis.getEnergy(), 0.00092D);
        }
    }

}
