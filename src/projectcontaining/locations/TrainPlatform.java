/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations;

import projectcontaining.AgvSystem.Agv;
import projectcontaining.Time;
import projectcontaining.Update;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author hielkehielkema93
 */
public class TrainPlatform implements Location, Update {

    @Override
    public void getContainer(Agv sender, ContainerData container) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setContainer(Agv sender, ContainerData container) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Time time, Time timeDif) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
