package de.zilchinger.vatsimdataapi;

/**
 * Interface for {$link {@link VatsimDataManager} to realize the Observer Pattern for clients that
 * has to be notified after a data update.
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
interface VatsimDataObservable {

    /**
     * Adds a client in order to notify it after a data update
     * 
     * @param client
     *            observer that will be notified after update
     */
    public void addVatsimDataUpdateListener(VatsimDataUpdate client);

    /**
     * Removes a client listener from the notification list
     * 
     * @param client
     */
    public void removeVatsimDataUpdateListener(VatsimDataUpdate client);

}
