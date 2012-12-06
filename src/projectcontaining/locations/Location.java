package projectcontaining.locations;

import projectcontaining.AgvSystem.Agv;
import projectcontaining.xmlparser.ContainerData;

/**
 * A interface for location that can contain containers
 * @author Hielke Hielkema
 */
public interface Location {
    
    /**
     * Asks the Platform to put the container on the agv and call agv.agvLoaded() when done
     * @param sender the agv that does the request
     * @param container the container to put on the agv
     */
    public void getContainer(Agv sender, ContainerData container);
    
    /**
     * Asks the Platform to pick-up the container from the agv and call agv.agvUnloaded() when done
     * @param sender the agv that does the request
     * @param container the container to pick-up from the agv
     */
    public void setContainer(Agv sender, ContainerData container);

    /**
     * Reset all data
     */
    public void reset();
}
