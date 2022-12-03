package edu.iit.workload.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vmAllocationPolicy;
    private String vmSelectionPolicy;
    private String parameter;
    private String workload;
    private String experimentName;
    private int numberOfHosts;
    private int numberOfVms;
    private double totalSimulationTime;
    private double energy;
    private double sla;
    private int numberOfMigrations;
    private double slaTimePerActiveHost;
    private double slaDegradationDueToMigration;
    private int numberOfHostShutdowns;
    private String groupId;

    @CreationTimestamp
    private LocalDateTime executedDate;

}
