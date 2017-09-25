package Model;

import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jens Larsen
 */
public class Product {

    public Product(ObservableList<Part> associatedParts, int productID, String name, 
            double price, int inStock, int min, int max) {
        this.associatedParts = associatedParts;
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    private ObservableList<Part> associatedParts;
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

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}