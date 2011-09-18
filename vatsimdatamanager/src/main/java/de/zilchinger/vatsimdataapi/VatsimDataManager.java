package de.zilchinger.vatsimdataapi;

import java.util.ArrayList;

import de.zilchinger.vatsimdataapi.exception.VatsimDataManagerException;
import de.zilchinger.vatsimdataapi.model.VatsimAirport;
import de.zilchinger.vatsimdataapi.model.VatsimController;
import de.zilchinger.vatsimdataapi.model.VatsimPilot;
import de.zilchinger.vatsimdataapi.model.VatsimServer;
import de.zilchinger.vatsimdataapi.model.VatsimStatus;
import de.zilchinger.vatsimdataapi.parser.ServerParser;
import de.zilchinger.vatsimdataapi.parser.StatusParser;
import de.zilchinger.vatsimdataapi.parser.UserParser;

/**
 * <p>
 * The VatsimDataManager is the main class of the API. It is responsible for parsing the online
 * Vatsim information and notifying clients that are registered for updates.
 * <p>
 * <p>
 * There are two possibilities to work with the Manager:
 * <ul>
 * <li>Use the default Constructor to initialize a VatsimDataManager</li>
 * <li>Or use {@link VatsimDataManager#getInstance()} to use the VatsimDataManager as a singleton</li>
 * </ul>
 * <p>
 * <p/>
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class VatsimDataManager implements VatsimDataObservable {

    /** List of all servers */
    private ArrayList<VatsimServer>     servers     = new ArrayList<VatsimServer>();
    /** List of all pilots */
    private ArrayList<VatsimPilot>      pilots      = new ArrayList<VatsimPilot>();
    /** List of all controllers */
    private ArrayList<VatsimController> controllers = new ArrayList<VatsimController>();
    /** List of fetched airports */
    private ArrayList<VatsimAirport>    airports    = new ArrayList<VatsimAirport>();
    /** Status that represents the parsed status url */
    private VatsimStatus                status;
    /** main URL */
    private String                      statusUrl;
    /** Singleton instance */
    private static VatsimDataManager    instance    = new VatsimDataManager();
    /** last data update */
    private long                        lastUpdate  = 0;
    /** all update listeners */
    private ArrayList<VatsimDataUpdate> listeners   = new ArrayList<VatsimDataUpdate>();

    /**
     * You can create a singleton VatsimDataManager to use it everywhere in your code. Alternatively
     * you can create an instance using the constructor call
     * 
     * @return instance of VatsimDataManager
     */
    public static VatsimDataManager getInstance() {
        return instance;
    }

    /**
     * Initializes the VatsimDataManger. This has to be done before using it.
     * 
     * @param url
     *            the url to the statuspage: e.g: http://status.vatsim.net/status.txt
     */
    public void init(String url) {
        statusUrl = url;
    }

    /**
     * Updates all Vatsim data and notifies the registered {@link VatsimDataUpdate}
     * 
     * @param progress
     *            callback to notify the caller about the current progress
     * @throws VatsimDataManagerException
     *             thrown if the VatsimDataManager is not initialized.
     */
    public void update(VatsimProgressUpdate progress) throws VatsimDataManagerException {
        lastUpdate = System.currentTimeMillis();
        StatusParser statusParser = new StatusParser(statusUrl, progress);
        status = statusParser.getVatsimStatus();
        if (status != null) {
            ServerParser serverParser = new ServerParser(status.getRandomServerUrl(), progress);
            servers = serverParser.getServers();
            UserParser userParser = new UserParser(status.getRandomDataUrl(), progress);
            controllers = userParser.getControllers();
            pilots = userParser.getPilots();
            airports = generateAirportList();
            updateDataListeners();
        } else {
            throw new VatsimDataManagerException(VatsimDataManagerException.NOT_INITIALIZED_MESSAGE);
        }

    }

    /**
     * @return true if data of servers, pilots and controllers are available
     */
    public boolean allDataAvailable() {
        if (servers.size() == 0 || pilots.size() == 0 || controllers.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * @return Timestamp of the last update
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @return a list of airports which are crawled from flight schedules of pilots
     */
    private ArrayList<VatsimAirport> generateAirportList() {
        ArrayList<VatsimAirport> airports = new ArrayList<VatsimAirport>();
        for (VatsimPilot pilot : pilots) {
            if (pilot.getFlightplanInfo().getDepartureAirport() != null) {
                if (airports.contains(pilot.getFlightplanInfo().getDepartureAirport()) == false) {
                    airports.add(pilot.getFlightplanInfo().getDepartureAirport());
                }
            }
            if (pilot.getFlightplanInfo().getDestinationAirport() != null) {
                if (airports.contains(pilot.getFlightplanInfo().getDestinationAirport()) == false) {
                    airports.add(pilot.getFlightplanInfo().getDestinationAirport());
                }
            }
        }
        return airports;
    }

    /** {@inheritDoc} */
    public void addVatsimDataUpdateListener(VatsimDataUpdate client) {
        listeners.add(client);
        client.update(pilots, servers, controllers, airports);
    }

    /** {@inheritDoc} */
    public void removeVatsimDataUpdateListener(VatsimDataUpdate client) {
        listeners.remove(client);

    }

    /**
     * update all clients, if all data are available
     */
    private void updateDataListeners() {
        if (allDataAvailable()) {
            for (VatsimDataUpdate client : listeners) {
                if (client != null) {
                    client.update(pilots, servers, controllers, airports);
                }
            }
        }
    }
}
