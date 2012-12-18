/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations;

import java.util.List;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author hielkehielkema93
 */
public class SeaShipPlatform implements Location, Update {
    
    @Override
    public void getContainer(Agv sender, ContainerData container) {

    }

    @Override
    public void setContainer(Agv sender, ContainerData container) {
 
    }

    @Override
    public void reset() {

    }

    @Override
    public void update(DateTime time, DateTime interval) {

    }

    @Override
    public List<ContainerData> getContainers() {
        return null;
    }

    @Override
    public void pushArrivedContainers(List<ContainerData> containers) {

    }
}
