package de.zilchinger.vatsimdataapi;

import java.util.ArrayList;

import de.zilchinger.vatsimdataapi.model.VatsimAirport;
import de.zilchinger.vatsimdataapi.model.VatsimController;
import de.zilchinger.vatsimdataapi.model.VatsimPilot;
import de.zilchinger.vatsimdataapi.model.VatsimServer;

public interface VatsimDataUpdate {

	public void update(ArrayList<VatsimPilot> pilots, ArrayList<VatsimServer> servers, ArrayList<VatsimController> controller, ArrayList<VatsimAirport> airports);;
}
