package de.zilchinger.vatsimdatamanager;

import java.util.ArrayList;

import de.zilchinger.vatsimdatamanager.model.VatsimAirport;
import de.zilchinger.vatsimdatamanager.model.VatsimController;
import de.zilchinger.vatsimdatamanager.model.VatsimPilot;
import de.zilchinger.vatsimdatamanager.model.VatsimServer;
import de.zilchinger.vatsimdatamanager.model.VatsimStatus;
import de.zilchinger.vatsimdatamanager.parser.ServerParser;
import de.zilchinger.vatsimdatamanager.parser.StatusParser;
import de.zilchinger.vatsimdatamanager.parser.UserParser;

public class VatsimDataManager implements VatsimDataObservable{

	public static final int PARSER_ERROR = 1;

	private ArrayList<VatsimServer> servers = new ArrayList<VatsimServer>();
	private ArrayList<VatsimPilot> pilots = new ArrayList<VatsimPilot>();
	private ArrayList<VatsimController> controllers = new ArrayList<VatsimController>();
	private ArrayList<VatsimAirport> airports = new ArrayList<VatsimAirport>();
	private VatsimStatus status;
	private String statusUrl;
	private static VatsimDataManager instance = new VatsimDataManager();
	private long lastUpdate = 0;

	private ArrayList<VatsimDataUpdate> listeners = new ArrayList<VatsimDataUpdate>();
	
	public static VatsimDataManager getInstance(){
		return instance;
	}

	public void init(String url) {
		statusUrl = url;
	}

	public void update(VatsimProgressUpdate progress){
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
		}
		updateDataListeners();
	}


	public boolean allDataAvailable() {
		if (servers.size() == 0 || pilots.size() == 0 || controllers.size() == 0) {
			return false;
		}
		return true;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

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

	public void addVatsimDataUpdateListener(VatsimDataUpdate client) {
		listeners.add(client);
		client.update(pilots, servers, controllers, airports);
	}

	public void removeVatsimDataUpdateListener(VatsimDataUpdate client) {
		listeners.remove(client);

	}

	public void updateDataListeners() {
		if (allDataAvailable()) {
			for (VatsimDataUpdate client : listeners) {
				if (client != null) {
					client.update(pilots, servers, controllers, airports);
				}
			}
		}
	}
}
