package edu.iit.workload.power.planetlab;

import java.io.IOException;

/**
 * A simulation of a heterogeneous power aware data center that applies the Median
 * Absolute Deviation (MAD) VM allocation policy and Minimum Migration Time (MMT) VM
 * selection policy.
 *
 * This example uses a real PlanetLab workload: 20110303.
 *
 * The remaining configuration parameters are in the Constants and PlanetLabConstants
 * classes.
 *
 * If you are using any algorithms, policies or workload included in the power package
 * please cite the following paper:
 *
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and
 * Adaptive Heuristics for Energy and Performance Efficient Dynamic Consolidation of
 * Virtual Machines in Cloud Data Centers", Concurrency and Computation: Practice and
 * Experience, ISSN: 1532-0626, Wiley Press, New York, USA, 2011, DOI: 10.1002/cpe.1867
 *
 * @author Anton Beloglazov
 * @since Jan 5, 2012
 */
public class MadMmt {

	/**
	 * The main method.
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		boolean enableOutput = true;
		boolean outputToFile = true;
		// String inputFolder =
		// MadMmt.class.getClassLoader().getResource("workload/planetlab").getPath();
		String inputFolder = "/Users/zenara/Documents/Research/project/cloudsim/src/examples/workload/planetlab";
		String outputFolder = "output";
		String workload = "20110412"; // PlanetLab workload
		String vmAllocationPolicy = "mad"; // Median Absolute Deviation (MAD) VM
											// allocation policy
		String vmSelectionPolicy = "mmt"; // Minimum Migration Time (MMT) VM selection
											// policy
		String parameter = "2.5"; // the safety parameter of the MAD policy
	}

}
