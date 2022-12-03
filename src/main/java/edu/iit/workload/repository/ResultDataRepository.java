package edu.iit.workload.repository;

import edu.iit.workload.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDataRepository extends JpaRepository<Result, Long> {

    @Query("SELECT o FROM Result o WHERE o.parameter = :parameter AND o.workload = :workload AND o.vmAllocationPolicy = :vmAllocationPolicy AND o.vmSelectionPolicy = :vmSelectionPolicy ")
    List<Result> findByParams(@Param("parameter") String parameter, @Param("workload") String workload, @Param("vmAllocationPolicy") String vmAllocationPolicy, @Param("vmSelectionPolicy") String vmSelectionPolicy);

}
