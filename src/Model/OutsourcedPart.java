package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jens Larsen
 */
public class OutsourcedPart extends Part {
    
    private StringProperty companyName;

    public OutsourcedPart(int partID, String name, double price, int inStock, int min, int max, String companyName) {
        super(partID, name, price, inStock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    
    public String getCompanyName() {
        return this.companyName.get();
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}
