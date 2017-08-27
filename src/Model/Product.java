package Model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;

/**
 *
 * @author Jens Larsen
 */
public class Product {

    private ArrayList<Part> associatedParts;
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return this.name.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getPrice() {
        return this.price.get();
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public int getInStock() {
        return this.inStock.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMin() {
        return this.min.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public int getMax() {
        return this.max.get();
    }

    public void addAssociatedPart(Part partToAdd) {
        associatedParts.add(partToAdd);
    }

    public boolean removeAssociatedPart(Part partToRemove) {

        if (associatedParts.contains(partToRemove)) {
            associatedParts.remove(partToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Part lookupAssociatedPart(int partID) {
        return associatedParts.get(partID);
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public int getProductID() {
        return this.productID.get();
    }
}