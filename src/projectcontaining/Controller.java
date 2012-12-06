/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining;

import java.util.List;
import projectcontaining.AgvSystem.AgvManager;
import projectcontaining.AgvSystem.AgvRequest;
import projectcontaining.locations.*;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.Date;
import projectcontaining.xmlparser.XmlFileParser;
/**
 *
 * @author hielkehielkema93
 */
public class Controller {
   
    // The locations
    private Location _seaShipPlatform;
    private Location _trainPlatform;
    private Location _riverBoatPlatform;
    private Location _truckPlatform;
    private ContainerStorage _containerStorage;
    
    // The container list
    private List<ContainerData> _containers;
    
    
    // The agv system
    private AgvManager _agvManager;
    
    // Current date and time
    private Date _date;
    private float _time;

    /**
     * Constructor
     */
    public Controller() {
        // Initalize parts
        this._seaShipPlatform = new SeaShipPlatform();
        this._trainPlatform = new TrainPlatform();
        this._riverBoatPlatform = new RiverBoatPlatform();
        this._truckPlatform = new TruckPlatform();
        this._containerStorage = new ContainerStorage();
        
        // agv system
        this._agvManager = new AgvManager();

        // Initialize date and time
        this._date = new Date(1,1,2012);
        this._time = 0.0f;
    }
    
    /**
     * 
     * @param filePath 
     */
    public void loadXmlFile(String filePath) {
        
        // Reset all variables
        this.reset();
        
        // Try to load the containers from the xml file
        this._containers = XmlFileParser.parseXmlFile(filePath);
        
        // Check if the loading succeeded
        if (this._containers == null) {
            // Error, Print an error message
            System.out.print("Error loading containers from xml file: " + filePath);
        }
        else {
            
            // Start the simulation
            this._date = new Date(1,1,2012);
            this._time = 0.0f;
        }
    }
    
    /**
     * Process a list of containers
     * @param containers 
     */
    private void ProcessContainers(List<ContainerData> containers) {
        // Process all containers in the list
        for(ContainerData container : containers) {
            // Check the time
            Date leaveDate = container.getLeaveDate();
            float leaveTime = container.getLeaveTimeFrom();
            int dateDif = leaveDate.compareTo(this._date);
            
            // Check if the date is now or after now
            if (dateDif == -1 || (dateDif == 0 && leaveTime <= this._time)) { 
                // Move container to the destination
                
                // Get the locations
                Location start = this.getLocationFromString(container.getEnterTransportType());
                Location end = this.getLocationFromString(container.getLeaveTransportType());
                
                // Add the agv request
                AgvRequest request = new AgvRequest(start, end, container);
                this._agvManager.addRequest(request);
            }
            else { 
                // Store the container
                
                // Get the locations
                Location start = this.getLocationFromString(container.getEnterTransportType());
                Location end = this._containerStorage;
                
                // Add the agv request
                AgvRequest request = new AgvRequest(start, end, container);
                this._agvManager.addRequest(request);
            }
        }
    }
    
    /**
     * Sort the containers depending on there position and leave time
     * @param containers 
     */
    private void SortContainers(List<ContainerData> containers) {
        // Sort containers here
    }
    
    public void startSimulation() {
        
    }
    
    public void stopSimulation() {
        
    }
    
    /**
     * Reset the system
     */
    public void reset() {
        // Reset all agv's
        this._agvManager.reset();
        
        // Reset all locations
        this._seaShipPlatform.reset();
        this._trainPlatform.reset();
        this._riverBoatPlatform.reset();
        this._truckPlatform.reset();
        this._containerStorage.reset();
    }
    
    /**
     * Check for containers that need to be moved from the storage
     */
    public void CheckStorageContainerDate() {
        /*
        // Get the stored containers
        List<ContainerData> storedContainers = this._containerStorage.getContainers();
        
        // Check all containers in storage
        int len = storedContainers.size();
        for(int i = 0; i < len; i++) {
            // Get the leave date and time
            Date leaveDate = storedContainers.get(i).getLeaveDate();
            float leaveTime = storedContainers.get(i).getLeaveTimeFrom();
            
            // Compare the dates
            int dateDif = leaveDate.compareTo(this._date);
            
            // Check if the date is now or after now
            if (dateDif == -1 || (dateDif == 0 && leaveTime <= this._time)) {
                
                // Get the end location
                Location endLocation = this.getLocationFromString(storedContainers.get(i).getLeaveTransportType());
                
                // Create and add the request
                AgvRequest request = new AgvRequest(this._containerStorage, endLocation, storedContainers.get(i));
                this._agvManager.addRequest(request);
            }
        }
        */
    }
    
    /**
     * Get the location for a location string
     * @param s trein | vrachtauto | binnenschip | zeeschip
     * @return 
     */
    private Location getLocationFromString(String s) {
        switch(s) {
            case "trein":
                return this._trainPlatform;
            
            case "vrachtauto":
                return this._truckPlatform;
            
            case "binnenschip":
                return this._riverBoatPlatform;
                
            case "zeeschip":
                return this._seaShipPlatform;
                
            default:
                return null;
        }
    }
}
