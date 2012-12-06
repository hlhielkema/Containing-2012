/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.AgvSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import projectcontaining.Time;
import projectcontaining.Update;

/**
 *
 * @author Hielke Hielkema & 
 */
public class AgvManager implements Update {
    
    // Private fields
    private Queue<AgvRequest> _requests;
    private List<Agv> _agvs;
    
    /**
     * Constructor
     */
    public AgvManager() {
        // Initlialize private fields
        this._requests = new LinkedList<>();
        this._agvs = new LinkedList<>();
        
        // Load the agvs
        final int agvCount = 100;
        for(int i = 0; i < agvCount; i++) {
            this._agvs.add(new Agv());
        }
    }
    
    /**
     * Add an agv request
     * @param request the agv request to add
     */
    public void addRequest(AgvRequest request) {
        // Get a free agv
        Agv agv = this.getFreeAgv();
        
        // Check if the was a free agv
        if(agv != null) {
            // Set the data for the agv
            agv.setData(request.getStart(), request.getEnd(), request.getContainer());
        }
        else {
            // Add the request to the request queue
            this._requests.add(request);
        }    
    }
    
    /**
     * Reset all data to it's default
     */
    public void reset() {
        // Clear request list
        this._requests.clear();
        
        // Reset all agvs
        int len = this._agvs.size();
        for(int i = 0; i < len; i++) {
            this._agvs.get(i).resetData();
        }
    }
    
    /**
     * Try to process the agv requests in the request queue
     */
    public void checkQueue() {
        // Try to find a agv for all items in the request queue
        while(!this._requests.isEmpty()) {
            // Get a free agv
            Agv agv = this.getFreeAgv();

            // Check if the was a free agv
            if(agv != null) {
                // Get the request from the queue
                AgvRequest request = this._requests.poll();
                
                // Set the data for the agv
                agv.setData(request.getStart(), request.getEnd(), request.getContainer());
            }
            else {
                // No free agv's, break
                break;
            }    
        }    
    }
    
    /**
     * Search for a not active agv
     * @return a not active agv, null if they are all in use
     */
    private Agv getFreeAgv() {
        
        // Optimalisation : get the nearest free agv
        
        int len = this._agvs.size();
        for(int i = 0; i < len; i++) {
            if (!this._agvs.get(i).getActive()) {
                return this._agvs.get(i);
            }
        }
        return null; // All in use
    }

    @Override
    public void update(Time time, Time timeDif) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
