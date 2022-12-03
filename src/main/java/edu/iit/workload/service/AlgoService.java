package edu.iit.workload.service;

import edu.iit.workload.domain.ExecutableData;
import edu.iit.workload.repository.ExecutableDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoService {

    private final ExecutableDataRepository executableDataRepository;

    public AlgoService(ExecutableDataRepository executableDataRepository) {
        this.executableDataRepository = executableDataRepository;
    }


    public List<ExecutableData> getAllExecutableData() {
        return this.executableDataRepository.findByActiveTrue();
    }

}
