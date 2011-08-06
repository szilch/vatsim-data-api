package de.zilchinger.vatsimdataapi.parser;

import java.util.ArrayList;

import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;
import de.zilchinger.vatsimdataapi.model.GeneralServerData;
import de.zilchinger.vatsimdataapi.model.VatsimServer;

/**
 * This specific parser, parses the {$link {@link VatsimServer}
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class ServerParser extends AVatsimParser {

    /** current section of the file to parse */
    private String                  section;
    /** The file to parse contains a general section, which is valid for each server */
    private GeneralServerData       generalData             = new GeneralServerData();
    /** List of servers */
    private ArrayList<VatsimServer> servers                 = new ArrayList<VatsimServer>();

    /** Tokens... */
    private static final String     GENERAL_SECTION         = "!GENERAL:";
    private static final String     SERVER_SECTION          = "!SERVERS:";
    private static final String     VERSION_TOKEN           = "version";
    private static final String     RELOAD_TOKEN            = "reload";
    private static final String     UPDATE_TOKEN            = "update";
    private static final String     ATIS_ALLOW_MIN_TOKEN    = "atic allow min";
    private static final String     CONNECTED_CLIENTS_TOKEN = "connected clients";

    /**
     * Constructor.
     * 
     * @param source
     * @param progress
     */
    public ServerParser(String source, VatsimProgressUpdate progress) {
        super(source, progress);
        setProgressState(VatsimProgressUpdate.SERVER_LOADING_STATE);
        parse();
    }

    /** {@inheritDoc} */
    @Override
    public void processLine(String line) {
        if (line.trim().startsWith(";")) {
            return;
        }
        if (line.contains(ERROR_TOKEN)) {
            servers = new ArrayList<VatsimServer>();
            return;
        }
        findSection(line);
        if (section == GENERAL_SECTION) {
            parseGeneralLine(line);
        } else if (section == SERVER_SECTION) {
            parseServerSection(line);
        }
    }

    /**
     * @param line
     *            string of server section will be parsed and an entity of {@link VatsimServer} will
     *            be created
     */
    private void parseServerSection(String line) {
        String[] result = line.split(":");
        if (result.length == 5) {
            servers.add(new VatsimServer(result[0], result[1], result[2], result[3], Boolean.getBoolean(result[4]), generalData));
        }
    }

    /**
     * Parses a general information line
     * 
     * @param line
     */
    private void parseGeneralLine(String line) {
        String[] result = line.split("=");
        if (result.length == 2) {
            String key = result[0].trim();
            String value = result[1].trim();
            if (key.toLowerCase().contains(VERSION_TOKEN)) {
                generalData.setVersion(Integer.parseInt(value));
            }
            if (key.toLowerCase().contains(RELOAD_TOKEN)) {
                generalData.setReload(Integer.parseInt(value));
            }
            if (key.toLowerCase().contains(UPDATE_TOKEN)) {
                generalData.setLastUpdate(value);
            }
            if (key.toLowerCase().contains(CONNECTED_CLIENTS_TOKEN)) {
                generalData.setConnectedClients(Integer.parseInt(value));
            }
            if (key.toLowerCase().contains(ATIS_ALLOW_MIN_TOKEN)) {
                generalData.setAtisAllowMin(Integer.parseInt(value));
            }
        }
    }

    /**
     * Tell the parser the current section
     * 
     * @param line
     */
    private void findSection(String line) {
        if (line.contains(GENERAL_SECTION)) {
            section = GENERAL_SECTION;
        }
        if (line.contains(SERVER_SECTION)) {
            section = SERVER_SECTION;
        }
    }

    /**
     * @return list of {@link VatsimServer}
     */
    public ArrayList<VatsimServer> getServers() {
        return servers;
    }

}
