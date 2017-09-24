package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jens Larsen
 */
public class Inventory {
    
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    public static ObservableList<Part> parts = FXCollections.observableArrayList();

    public static void addProduct(Product productToAdd) {
        Inventory.products.add(productToAdd);
    }
    
    public static boolean removeProduct(Product productToRemove) {
        if(products.contains(productToRemove)) {
            products.remove(productToRemove);
            return true;
        } else {
            return false;
        }
    }
    
    public static Product lookupProduct(int productID) {
        return products.get(productID);
    }
    
    public static void updateProduct(int productID) {
        // TODO I don't know what this method is supposed to do???
    }
    
    public static void addPart(Part partToAdd) {
        Inventory.parts.add(partToAdd);
    }
    
    public static boolean deletePart(Part partToDelete) {
        if(parts.contains(partToDelete)) {
            parts.remove(partToDelete);
            return true;
        } else {
            return false;
        }
    }
    
    public static Part lookupPart(int partID) {
        return Inventory.parts.get(partID);
    }
    
    public static void updatePart(int partID, Part partToUpdate) {
        Inventory.parts.set(partID, partToUpdate);
    }
}
