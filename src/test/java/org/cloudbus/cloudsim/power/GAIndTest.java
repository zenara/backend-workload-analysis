package org.cloudbus.cloudsim.power;

import edu.iit.workload.power.Helper;
import edu.iit.workload.power.planetlab.PlanetLabConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GAIndTest {

    protected static List<PowerHost> hostList;

    PowerVmAllocationPolicyMigrationGA powerVmAllocationPolicyMigrationGA;
    PowerVmSelectionPolicy vmSelectionPolicy = null;

    GAInd gaInd = null;

    @BeforeEach
    void before() throws Exception {
        hostList = Helper.createHostList(PlanetLabConstants.NUMBER_OF_HOSTS);
        vmSelectionPolicy = getVmSelectionPolicy("mu");
        powerVmAllocationPolicyMigrationGA = new PowerVmAllocationPolicyMigrationGA(hostList, vmSelectionPolicy);
        gaInd = new GAInd(powerVmAllocationPolicyMigrationGA);
    }

    protected PowerVmSelectionPolicy getVmSelectionPolicy(String vmSelectionPolicyName) {
        PowerVmSelectionPolicy vmSelectionPolicy = null;
        if (vmSelectionPolicyName.equals("mc")) {
            vmSelectionPolicy = new PowerVmSelectionPolicyMaximumCorrelation(
                    new PowerVmSelectionPolicyMinimumMigrationTime());
        }
        else if (vmSelectionPolicyName.equals("mmt")) {
            vmSelectionPolicy = new PowerVmSelectionPolicyMinimumMigrationTime();
        }
        else if (vmSelectionPolicyName.equals("mu")) {
            vmSelectionPolicy = new PowerVmSelectionPolicyMinimumUtilization();
        }
        else if (vmSelectionPolicyName.equals("rs")) {
            vmSelectionPolicy = new PowerVmSelectionPolicyRandomSelection();
        }
        else {
            System.out.println("Unknown VM selection policy: " + vmSelectionPolicyName);
            System.exit(0);
        }
        return vmSelectionPolicy;
    }

    @Test
    void getFitness() {
        double[] fitness = gaInd.getFitness();
        assertEquals(fitness[0],    800);
    }

}
