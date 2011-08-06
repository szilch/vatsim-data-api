package de.zilchinger.vatsimdataapi.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;

/**
 * The Abstract Vatsim Parser is the generalization for each parser used to handle different data
 * like controllers, pilots, servers etc.
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public abstract class AVatsimParser {

    /** logical token that represents the end */
    protected static final String END_TOKEN     = "END_END_END;";
    /** logical token that represents an error */
    protected static final String ERROR_TOKEN   = "ERROR_ERROR_ERROR;";

    /** Source Url where to fetch the file */
    private String                source;
    /** Callback for sending progress information */
    private VatsimProgressUpdate  progress;
    private int                   progressState = 0;

    /**
     * Constructor.
     * 
     * @param source
     *            url of file to parse
     * @param progress
     *            callback for sending progress information
     */
    protected AVatsimParser(String source, VatsimProgressUpdate progress) {
        this.source = source;
        this.progress = progress;
    }

    /**
     * parses an entity line e.g. for a pilot, controller or server
     * 
     * @param line
     */
    protected abstract void processLine(String line);

    /**
     * Parses a file received from the source url and calls {$link {@link #processLine(String)} for
     * a specific entity.
     */
    protected void parse() {
        InputStream input = null;
        int currentProgress = 0;
        int totalProgress = 0;
        try {
            if (source == null) {
                processLine(ERROR_TOKEN);
                return;
            }
            URLConnection urlConnection = new URL(source).openConnection();
            totalProgress = urlConnection.getContentLength();
            input = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(input));
            String line;
            int sendProgressFlag = 0;
            while ((line = r.readLine()) != null) {
                sendProgressFlag++;
                currentProgress += line.length();
                processLine(line);
                if (sendProgressFlag % 20 == 0) {
                    progress.updateProgress((int) ((currentProgress / (float) totalProgress) * 100), progressState);
                }
            }
            processLine(END_TOKEN);
        } catch (MalformedURLException e) {
            processLine(ERROR_TOKEN);
        } catch (IOException e) {
            processLine(ERROR_TOKEN);
        }
    }

    /**
     * @param progressState
     *            state during parse process
     */
    protected void setProgressState(int progressState) {
        this.progressState = progressState;
    }
}
