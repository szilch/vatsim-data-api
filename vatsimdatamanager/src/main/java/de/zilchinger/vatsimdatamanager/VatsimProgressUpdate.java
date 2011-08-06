package de.zilchinger.vatsimdatamanager;

public interface VatsimProgressUpdate {

	public static final int SERVER_LOADING_STATE = 1;
	public static final int USER_LOADING_STATE = 2;
	
	public void updateProgress (int progress, int state);
}
