package de.zilchinger.vatsimdatamanager.parser;

import java.util.ArrayList;

import de.zilchinger.vatsimdatamanager.VatsimProgressUpdate;
import de.zilchinger.vatsimdatamanager.model.VatsimController;
import de.zilchinger.vatsimdatamanager.model.VatsimPilot;

public class UserParser extends AVatsimParser{
	
	private static final String CLIENT_SECTION = "!CLIENTS:";
	private static final String PILOT_TYPE ="PILOT";
	private static final String CONTROLLER_TYPE ="ATC";
	private String section="";
	
	private ArrayList<VatsimPilot> pilots = new ArrayList<VatsimPilot>();
	private ArrayList<VatsimController> controllers = new ArrayList<VatsimController>();
	
	
	public UserParser(String source, VatsimProgressUpdate progress) {
		super(source, progress);
		setProgressState(VatsimProgressUpdate.USER_LOADING_STATE);
		parse();
	}

	@Override
	public void processLine(String line) {
		if (line.contains(ERROR_TOKEN)) {
			controllers = new ArrayList<VatsimController>();
			pilots = new ArrayList<VatsimPilot>();
			return;
		}
		findSection(line);
		if (section.equalsIgnoreCase(CLIENT_SECTION)) {
			parseClientLine(line);
		}
	}
	

	private void parseClientLine(String line) {
		line +=END_TOKEN;
		String[] result = line.split(":");
		if (result.length == 42) {
			if (result[3].contains(PILOT_TYPE)) {
				pilots.add(new VatsimPilot(result));
			}
			if (result[3].contains(CONTROLLER_TYPE)) {
				controllers.add(new VatsimController(result));
			}
			
		}
		
	}
	
	public ArrayList<VatsimPilot> getPilots() {
		return pilots;
	}

	public ArrayList<VatsimController> getControllers() {
		return controllers;
	}

	private void findSection(String line) {
		if (line.contains(CLIENT_SECTION)) {
			section = CLIENT_SECTION;
		}
	}

}
