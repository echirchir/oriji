package com.oriji.products.events;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the EventBus instance that will fire
 * once the event has completed - getting results from the server
 */

public class SyncCompletedEvent {
    private boolean mFinished;

    public SyncCompletedEvent(boolean finished){
        mFinished = finished;
    }

    /**
     *
     * @return whether an event has finished or not
     */
    public boolean isFinished(){
        return mFinished;
    }
}
