package edu.iit.workload.api;

import edu.iit.workload.power.planetlab.PlanetLabRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class AlgoController {

    private final PlanetLabRunner planetLabRunner;

    public AlgoController(PlanetLabRunner planetLabRunner) {
        this.planetLabRunner = planetLabRunner;
    }

    @GetMapping("/execute")
    public void execute() {
        boolean enableOutput = true;
        boolean outputToFile = true;
        String inputFolder = "H:\\Chathu\\backend\\backend-workload-analysis\\src\\main\\resources\\workload\\";
        String outputFolder = "H:\\Chathu\\backend\\backend-workload-analysis\\src\\main\\resources\\output";
        String workload = "20110420"; // PlanetLab workload
        String vmAllocationPolicy = "ga"; // Inter Quartile Range (IQR) VM allocation policy
        String vmSelectionPolicy = "mmt"; // Minimum Migration Time (MMT) VM selection policy
        String parameter = "1.5"; // the safety parameter of the IQR policy
        planetLabRunner.run(
                enableOutput,
                outputToFile,
                inputFolder,
                outputFolder,
                workload,
                vmAllocationPolicy,
                vmSelectionPolicy,
                parameter);
        System.out.println("A");
    }

}
