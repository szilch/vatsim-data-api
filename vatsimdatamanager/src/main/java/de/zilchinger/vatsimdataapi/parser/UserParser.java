package de.zilchinger.vatsimdataapi.parser;

import java.util.ArrayList;

import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;
import de.zilchinger.vatsimdataapi.model.VatsimController;
import de.zilchinger.vatsimdataapi.model.VatsimPilot;

/**
 * This parser parses the user data and detects controllers and pilots
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class UserParser extends AVatsimParser {

    /** Tokens... */
    private static final String         CLIENT_SECTION  = "!CLIENTS:";
    private static final String         PILOT_TYPE      = "PILOT";
    private static final String         CONTROLLER_TYPE = "ATC";
    private String                      section         = "";

    /** list of pilots */
    private ArrayList<VatsimPilot>      pilots          = new ArrayList<VatsimPilot>();
    /** list of controllers */
    private ArrayList<VatsimController> controllers     = new ArrayList<VatsimController>();

    /**
     * Constructor.
     * 
     * @param source
     * @param progress
     */
    public UserParser(String source, VatsimProgressUpdate progress) {
        super(source, progress);
        setProgressState(VatsimProgressUpdate.USER_LOADING_STATE);
        parse();
    }

    /** {@inheritDoc} */
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

    /**
     * @param line
     *            string of a clinet line (controller or pilot)
     */
    private void parseClientLine(String line) {
        line += END_TOKEN;
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

    /**
     * Tell the parser the current section
     * 
     * @param line
     */
    private void findSection(String line) {
        if (line.contains(CLIENT_SECTION)) {
            section = CLIENT_SECTION;
        }
    }

    /**
     * @return list of {@link VatsimPilots}
     */
    public ArrayList<VatsimPilot> getPilots() {
        return pilots;
    }

    /**
     * @return list of {@link VatsimController}
     */
    public ArrayList<VatsimController> getControllers() {
        return controllers;
    }

}
