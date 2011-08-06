package de.zilchinger.vatsimdataapi;

import java.util.ArrayList;

import de.zilchinger.vatsimdataapi.model.VatsimAirport;
import de.zilchinger.vatsimdataapi.model.VatsimController;
import de.zilchinger.vatsimdataapi.model.VatsimPilot;
import de.zilchinger.vatsimdataapi.model.VatsimServer;

/**
 * This is the callback for update notifications of the {@link VatsimDataManager}.
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public interface VatsimDataUpdate {

    /**
     * Notify all listeners
     * 
     * @param pilots
     *            list of all pilots
     * @param servers
     *            list of all servers
     * @param controllers
     *            list of all controllers
     * @param airports
     *            list of all airports
     */
    public void update(ArrayList<VatsimPilot> pilots, ArrayList<VatsimServer> servers, ArrayList<VatsimController> controllers,
            ArrayList<VatsimAirport> airports);;
}
