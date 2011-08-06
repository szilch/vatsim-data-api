package de.zilchinger.vatsimdataapi;

/**
 * Notifier for progress updates during data loading
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public interface VatsimProgressUpdate {

    /** Loading server data */
    public static final int SERVER_LOADING_STATE = 1;
    /** Loading user data */
    public static final int USER_LOADING_STATE   = 2;

    /**
     * @param progress
     *            [0,100] the current progress in percent in reference to the state.
     * @param state
     *            represents the loading state. Could be: {@link #SERVER_LOADING_STATE} or
     *            {@link #USER_LOADING_STATE}
     */
    public void updateProgress(int progress, int state);
}
