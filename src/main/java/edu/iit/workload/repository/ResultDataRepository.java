package edu.iit.workload.repository;

import edu.iit.workload.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDataRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByWorkloadAndParameterAndVmAllocationPolicyAndVmSelectionPolicy(String parameter, String workload, String vmAllocationPolicy, String vmSelectionPolicy);

}
