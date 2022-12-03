package edu.iit.workload.repository;

import edu.iit.workload.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultDataRepository extends JpaRepository<Result, Long> {

}
