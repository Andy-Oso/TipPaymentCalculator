package project3;

/**
 * The Order class definition. To be used in the TipCalculator JavaFX application
 * class.
 * 
 * @author Andy Osorio / Justin Moran / Thomas Lawless 
 * SCCC CST242, Spring 2019
 */
public class Order {
    
    // instance variables
    private String item;
    private double price;
    
    /**
     * The Default constructor for Order objects. Items are instantiated to an empty
     * string and prices are instantiated to "0.0"
     */
    public Order(){
        this("", 0);
    }
    
    /**
     * The constructor for an Order object, for use in the TipCalculator class
     * @param item The item name of the order as a String
     * @param price the price of the order as a double
     */
    public Order(String item, double price){
        this.setItem(item);
        this.setPrice(price);
    }
    
    /**
     * Returns the name of the object 
     * @return 
     */
    public String getItem(){
        return this.item;
    }
    
    /**
     * 
     * @return 
     */
    public double getPrice(){
        return this.price;
    }
    
    /**
     * 
     * @param item 
     */
    public void setItem(String item){
        this.item = item;
    }
    
    /**
     * 
     * @param price 
     */
    public void setPrice(double price){
        if(price > 0.0){
            this.price = price;
        }else{
            this.price = 0.0;
        }
    }
}