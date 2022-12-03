package edu.iit.workload.repository;

import edu.iit.workload.domain.ExecutableData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutableDataRepository extends JpaRepository<ExecutableData, Long> {

	List<ExecutableData> findByActiveTrue();

}
