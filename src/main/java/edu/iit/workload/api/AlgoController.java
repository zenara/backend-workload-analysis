package edu.iit.workload.api;

import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.power.planetlab.PlanetLabRunner;
import edu.iit.workload.service.AlgoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class AlgoController {

    private final PlanetLabRunner planetLabRunner;

    private final AlgoService algoService;

    @Value("${folder.input}")
    private String inputFolder;

    @Value("${folder.output}")
    private String outputFolder;

    boolean enableOutput = true;

    boolean outputToFile = true;

    public AlgoController(PlanetLabRunner planetLabRunner, AlgoService algoService) {
        this.planetLabRunner = planetLabRunner;
        this.algoService = algoService;
    }

    /**
     * @param VMallocation Inter Quartile Range (IQR) VM allocation policy
     * @param VMselection  Minimum Migration Time (MMT) VM selection policy
     * @param parameter  the safety parameter of the IQR policy
     */
    @GetMapping("/execute/{id}")
    public void execute(@PathVariable("id") Long id) {
        Optional<ExecutableData> byId = this.algoService.findById(id);
        UUID uuid = UUID.randomUUID();
        if(byId.isPresent()){
            String workload = "20110420"; // PlanetLab workload
            planetLabRunner.run(
                    enableOutput,
                    outputToFile,
                    inputFolder,
                    outputFolder,
                    byId.get(), uuid.toString());
        }
    }

    @GetMapping("/execute")
    public String execute() {
        List<ExecutableData> allExecutableData = this.algoService.getAllExecutableData();
        UUID uuid = UUID.randomUUID();
        if (allExecutableData != null) {
            allExecutableData.forEach(executableData -> {
                planetLabRunner.run(
                        enableOutput,
                        outputToFile,
                        inputFolder,
                        outputFolder,
                        executableData, uuid.toString());
            });
        }
        return "" + allExecutableData.size() + " item/s executed";
    }

}
