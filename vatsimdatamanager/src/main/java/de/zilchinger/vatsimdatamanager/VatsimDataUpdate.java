package de.zilchinger.vatsimdatamanager;

import java.util.ArrayList;

import de.zilchinger.vatsimdatamanager.model.VatsimAirport;
import de.zilchinger.vatsimdatamanager.model.VatsimController;
import de.zilchinger.vatsimdatamanager.model.VatsimPilot;
import de.zilchinger.vatsimdatamanager.model.VatsimServer;

public interface VatsimDataUpdate {

	public void update(ArrayList<VatsimPilot> pilots, ArrayList<VatsimServer> servers, ArrayList<VatsimController> controller, ArrayList<VatsimAirport> airports);;
}
