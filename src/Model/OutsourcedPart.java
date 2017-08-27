package Model;

import javafx.beans.property.StringProperty;

/**
 *
 * @author Jens Larsen
 */
public class OutsourcedPart extends Part {
    
    private StringProperty companyName;
    
    public String getCompanyName() {
        return this.companyName.get();
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}
