package edu.iit.workload.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ExecutableData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String vmAllocationPolicy;

	private String vmSelectionPolicy;

	private String parameter;

	private String workload;

	private boolean active;

}
