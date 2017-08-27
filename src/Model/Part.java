package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
        
/**
 *
 * @author Jens Larsen
 */
abstract public class Part {
    
    private IntegerProperty partID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public int getInStock(){
        return this.inStock.get();
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public int getMax() {
        return this.max.get();
    }   
}