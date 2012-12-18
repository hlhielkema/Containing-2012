/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations;

import ProjectContaining.locations.Crane;
import java.util.ArrayList;
import java.util.List;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.Point3D;

/**
 *
 * @author hielkehielkema93
 */
public class TrainPlatform implements Location, Update {

    List<ProjectContaining.locations.Crane> _cranes = new ArrayList();
    List<ContainerData> _containers = new ArrayList();
    public TrainPlatform()
    {
       Crane crane = new ProjectContaining.locations.Crane(0);
       for (int i =0;i<4;i++)
       {
            _cranes.add(crane); // create 4 cranes
       }
    }
    
    @Override
    public void getContainer(Agv sender, ContainerData container)
    {
        if (getDoneCrane() == null)
        {
            return;
        }
        sender.agvLoaded();
        getDoneCrane().removeContainer();
    }

    @Override
    public void setContainer(Agv sender, ContainerData container)
    {
        if (getIdleCrane() == null)
        {
            return;
        }
        int x = container.getLocation().getX();
        getIdleCrane().setContainer(container, x);
        sender.agvUnloaded();
    }

    @Override
    public void reset() 
    {
        _cranes.clear();
        _containers.clear();
    }
    
    private ProjectContaining.locations.Crane getDoneCrane()
    {
        for (ProjectContaining.locations.Crane crane : _cranes)
        {
            if (crane.getProgress() == 1)
            { 
                return crane;
            }
        }
        return null;
    }
    
    private ProjectContaining.locations.Crane getIdleCrane()
    {
        for (ProjectContaining.locations.Crane crane : _cranes)
        {
            if (!crane._isActive)
            {
                return crane;
            }
        }
        return null;
    }
    
    @Override
    public void update(DateTime time, DateTime interval)
    {
        if (_containers.size() > 0 && getIdleCrane() != null)
        {
            ContainerData container = _containers.get(0);
            Point3D position  = container.getLocation();
            int x  = position.getX();
            ProjectContaining.locations.Crane crane = getIdleCrane();
            crane.setContainer(_containers.get(0), x);
        }
    } 
    
    @Override
    public List<ContainerData> getContainers() {
        return _containers;
    }

    @Override
    public void pushArrivedContainers(List<ContainerData> containers)
    {
        _containers.addAll(containers);
    }
    
}
