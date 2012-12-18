/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectContaining.locations;

import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author Bart
 * Crane Class for loading and unloading containers
 */
public class Crane implements Update
{
    float _loadProgress; // indicates the progress of the loading
    float _travelProgress; // indicates the progress of the travel
    
    public boolean _isActive; // true if the crane is holding a container
    ContainerData _currentContainer; // the container that is being held
    
    final float _speed; // the constant speed of the crane
    final float _loadTime; // the time needed to load or unload an container in seconds
    
    float _travelTime; // the time needed by the crane to travel 
    float _totalDistance; // the distance the crane has to travel 
    float _traveledDistance; // the distance the crane has traveled
    
    int _position;
    public Crane(int position )
    {  
        _position = position;
        _speed = 5;
        _travelProgress = 0;
        _travelTime = 0;
        _loadTime = 10;
        _loadProgress = 0;
        _isActive = false;
        _traveledDistance = 0;
        _totalDistance = 0;
    }
    @Override
    public void update(DateTime time, DateTime interval)
     {
        _traveledDistance += interval.getSeconds() * _speed ;
        _travelProgress = _traveledDistance / _totalDistance;
        if (_travelProgress > 1)
        {
            _travelProgress = 1;
            _loadProgress += interval.getSeconds() / _loadTime;
            if (_loadProgress > 1)
            {
                _loadProgress = 1;
            }
        }
    }

     /**
     * Returns the container that is being held. 
     * returns null if the crane is not carrying any
     */
    public ContainerData getContainer()
    {
        return _currentContainer;
    }

    /**
     * Drops/Removes the container from the Crane.
     * Should be called when the crane is done.
     */
    public void removeContainer()
    {
        _currentContainer = null;
        _isActive = false;
        _loadProgress = 0;
        _travelProgress = 0;
    }
    
    /*
     * Returns the progress of the crane
     * Returns 1 if the crane is done
     */
    public float getProgress()
    {
        if (_loadTime + _travelTime == 0)
        {
    		return 0;
        }
        float tTimeProgress =_travelTime *  _travelProgress;
        float lTimeProgress = _loadTime * _loadProgress;
        return (tTimeProgress + lTimeProgress) / (_loadTime + _travelTime);
    }
        
    
    /*
     * Starts to load a container if the crane is idle
     * returns false if the crane is active
     */ 
    public void setContainer(ContainerData container,int destination)
    {
        if (_isActive)
        {
           return;
        }
        
        _totalDistance = Math.abs(_position - destination);
        _travelTime = _totalDistance / _speed;
        _currentContainer = container;
        _isActive = true;
    }
}
