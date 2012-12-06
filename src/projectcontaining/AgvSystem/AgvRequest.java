package projectcontaining.AgvSystem;

import projectcontaining.locations.Location;
import projectcontaining.xmlparser.ContainerData;

/**
 * A data object to store agv requests used by the agv manager
 * @author Hielke Hielkema
 */
public class AgvRequest {
    
    // Private fields
    private Location _start;
    private Location _end;
    private ContainerData _container;
     
    /**
     * Constructor
     * @param start start location for agv
     * @param end destination location for the agv
     * @param container the container to transport
     */
    public AgvRequest(Location start, Location end, ContainerData container) {
        this._start = start;
        this._end = end;
        this._container = container;
    }

    /**
     * Gets the start location for the agv
     * @return the start location
     */
    public Location getStart() {
        return this._start;
    }
    
    /**
     * Get the end location for the agv
     * @return the end location
     */
    public Location getEnd() {
        return this._end;
    }
    
    /**
     * Gets the container to transport
     * @return the container to transport
     */
    public ContainerData getContainer() {
        return this._container;
    }
}
