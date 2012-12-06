package projectcontaining.AgvSystem;

import projectcontaining.Time;
import projectcontaining.Update;
import projectcontaining.locations.Location;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author Hielke Hielkema & 
 */
public class Agv implements Update {
    
    // Private fields
    private Location _start;
    private Location _end;
    private ContainerData _container;
    private boolean _active;
    private boolean _waiting;
    private boolean _loaded;
    
    /**
     * Empty Constructor
     */
    public Agv() {
        this._active = false;
        this._waiting = false;
        this._loaded = false;
    }
    
    /**
     * gets if the agv is active or waiting
     * @return a boolean indicating if the agv is active
     */
    public boolean getActive() {
        return this._active;
    }
        
    /**
     * gets if the agv is waiting for the container to be placed
     * @return a boolean indicating if the agv is waiting
     */
    public boolean getWaiting() {
        return this._waiting;
    }
    
    /**
     * Get the max speed of the agv
     * @return the max speed of the agv
     */
    public int getMaxSpeed() {
        return _loaded ? 20:40;
    }
    
    /**
     * Call this when the start location is reached
     */
    public void startLocationReached() {
        // Set waiting to true
        this._waiting = true;
        
        // Request the container
        this._start.getContainer(this, _container);
    }
    
    /**
     * Call this when the agv is loaded
     */
    public void agvLoaded() {
        this._waiting = false;
        this._loaded = true;
    }
    
    /**
     * Call this when the end location is reached
     */
    public void endLocationReached() {
        // Set waiting to true
        this._waiting = true;
        
        // Request the container to be picked up
        this._start.setContainer(this, _container);
    }
    
    /**
     * Call this when the agv is unloaded
     */
    public void agvUnloaded() {
        this.resetData();
    }
    
    /**
     * Set the agv data
     * @param start start position for the agv
     * @param end end position for the agv
     * @param container the container to get
     */
    public void setData(Location start, Location end, ContainerData container) {
        this._active = true;
        this._start = start;
        this._end = end;
        this._container = container;
        this._loaded = false;
        this._waiting = false;
    }
    
    /**
     * Reset the agv data
     */
    public void resetData() {
        this._active = false;
        this._start = null;
        this._end = null;
        this._container = null;
        this._loaded = false;
        this._waiting = false;
    }

    @Override
    public void update(Time time, Time timeDif) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
