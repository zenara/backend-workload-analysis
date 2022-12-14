package edu.iit.workload.repository;

import edu.iit.workload.domain.ResultAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDataRepository extends JpaRepository<ResultAnalysis, Long> {

	List<ResultAnalysis> findAllByWorkloadAndParameterAndVmAllocationPolicyAndVmSelectionPolicy(String parameter,
			String workload, String vmAllocationPolicy, String vmSelectionPolicy);

	List<ResultAnalysis> findByOrderByExecutedDateDesc();
}
