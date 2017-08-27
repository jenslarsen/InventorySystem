package Model;

import javafx.beans.property.IntegerProperty;

/**
 *
 * @author Jens Larsen
 */
public class InhousePart extends Part {
    
    private IntegerProperty machineID;
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return this.machineID.get();
    }
}
