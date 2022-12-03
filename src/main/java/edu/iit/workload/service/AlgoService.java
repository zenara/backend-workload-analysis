package edu.iit.workload.service;

import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.domain.Result;
import edu.iit.workload.repository.ExecutableDataRepository;
import edu.iit.workload.repository.ResultDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlgoService {

    private final ExecutableDataRepository executableDataRepository;

    private final ResultDataRepository resultDataRepository;

    public AlgoService(ExecutableDataRepository executableDataRepository, ResultDataRepository resultDataRepository) {
        this.executableDataRepository = executableDataRepository;
        this.resultDataRepository = resultDataRepository;
    }


    public List<ExecutableData> getAllExecutableData() {
        return this.executableDataRepository.findByActiveTrue();
    }

    public Optional<ExecutableData> findById(Long id) {
        return this.executableDataRepository.findById(id);
    }

    public boolean checkIsExecuted(ExecutableData executableData) {
        List<Result> byParams = resultDataRepository.findByParams(executableData.getParameter(),
                executableData.getWorkload(),
                executableData.getVmAllocationPolicy(), executableData.getVmSelectionPolicy());
        if(byParams != null && byParams.size()>0){
            return true;
        }
        return false;
    }
}
