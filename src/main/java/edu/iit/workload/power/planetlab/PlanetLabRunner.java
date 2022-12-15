package edu.iit.workload.power.planetlab;

import java.util.Calendar;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudSim;
import edu.iit.workload.power.Helper;
import edu.iit.workload.power.RunnerAbstract;
import org.springframework.stereotype.Service;

/**
 * The example runner for the PlanetLab workload.
 * <p>
 * If you are using any algorithms, policies or workload included in the power package
 * please cite the following paper:
 * <p>
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and
 * Adaptive Heuristics for Energy and Performance Efficient Dynamic Consolidation of
 * Virtual Machines in Cloud Data Centers", Concurrency and Computation: Practice and
 * Experience, ISSN: 1532-0626, Wiley Press, New York, USA, 2011, DOI: 10.1002/cpe.1867
 *
 * @author Anton Beloglazov
 * @since Jan 5, 2012
 */
@Service
public class PlanetLabRunner extends RunnerAbstract {

	@Override
	public void init(String inputFolder) {
		try {
			CloudSim.init(1, Calendar.getInstance(), false);

			broker = Helper.createBroker();
			int brokerId = broker.getId();

			cloudletList = PlanetLabHelper.createCloudletListPlanetLab(brokerId, inputFolder);
			vmList = Helper.createVmList(brokerId, cloudletList.size());
			hostList = Helper.createHostList(PlanetLabConstants.NUMBER_OF_HOSTS);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.printLine("The simulation has been terminated due to an unexpected error");
			System.exit(0);
		}
	}

}
