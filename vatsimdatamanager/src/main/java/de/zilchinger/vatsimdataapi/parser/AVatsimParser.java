package de.zilchinger.vatsimdataapi.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;

public abstract class AVatsimParser {

	
	protected static final String END_TOKEN = "END_END_END;";
	protected static final String ERROR_TOKEN = "ERROR_ERROR_ERROR;";
	
	private String source;
	private VatsimProgressUpdate progress;
	private int progressState = 0;

	public AVatsimParser(String source, VatsimProgressUpdate progress) {
		this.source = source;
		this.progress = progress;
	}
	
	protected abstract void processLine(String line);
	
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
	
	protected void setProgressState(int progressState) {
		this.progressState = progressState;
	}
}
