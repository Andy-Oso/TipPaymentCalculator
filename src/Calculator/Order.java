package Calculator;

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
     * 
     */
    public Order(){
        this("", 0);
    }
    
    /**
     * 
     * @param item
     * @param price 
     */
    public Order(String item, double price){
        this.setItem(item);
        this.setPrice(price);
    }
    
    /**
     * 
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
