package edu.iit.workload.api;

import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.power.planetlab.PlanetLabRunner;
import edu.iit.workload.service.AlgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * @param allocation Inter Quartile Range (IQR) VM allocation policy
     * @param selection  Minimum Migration Time (MMT) VM selection policy
     * @param parameter  the safety parameter of the IQR policy
     */
    @GetMapping("/execute/{allocation}/{selection}/{parameter}")
    public void execute(@PathVariable("allocation") String allocation,
                        @PathVariable("selection") String selection, @PathVariable("parameter") String parameter) {
        String workload = "20110420"; // PlanetLab workload
        planetLabRunner.run(
                enableOutput,
                outputToFile,
                inputFolder,
                outputFolder,
                workload,
                allocation,
                selection,
                parameter);
    }

    @GetMapping("/execute")
    public void execute() {
        List<ExecutableData> allExecutableData = this.algoService.getAllExecutableData();
        if (allExecutableData != null) {
            allExecutableData.forEach(executableData -> {
                planetLabRunner.run(
                        enableOutput,
                        outputToFile,
                        inputFolder,
                        outputFolder,
                        executableData.getWorkload(),
                        executableData.getVmAllocationPolicy(),
                        executableData.getVmSelectionPolicy(),
                        executableData.getParameter());
            });
        }
    }

}
