package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Jens Larsen
 */
public class InhousePart extends Part {

    public InhousePart() {
        super();
        this.machineID.set(0);
    }

    private IntegerProperty machineID;

    public InhousePart(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super(partID, name, price, inStock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return this.machineID.get();
    }
}
