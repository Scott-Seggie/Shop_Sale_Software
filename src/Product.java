/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author Scott
 */

/* implements serializable allows me to write and Object to a file and read 
the file when needed, I will use this to hold all my products.
*/
public class Product implements Serializable {
    
    // varibles 
    private String name;
    private int stocklevel;
    private double price;  
    
    

    // constructor 
    public Product(String name, int stocklevel, double price) {
        this.name = name;
        this.stocklevel = stocklevel;
        this.price = price;
    }
    
    /* I am using this equals methods to compare prices and name to make sure they
    are pointing to the same memory loacation (they are the same Object).
    */
      public boolean equals (Product product)
    {
       return this.price == product.price && this.name.equals(product.name);
    }
    
      //restock method
    public void reStock (int stockin)
   {
     this.stocklevel = stocklevel + stockin;
   }
   
   //sell mothod 
   public double sell (int quantity)
   {
    if (quantity <= this.stocklevel)
    {
      return this.stocklevel = stocklevel - quantity;   
     
    }
    else 
    {
     return this.stocklevel;
    }
   }

    //getters
    public String getName() {
        return name;
    }

    public int getStocklevel() {
        return stocklevel;
    }

    public double getPrice() {
        return price;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStocklevel(int stocklevel) {
        this.stocklevel = stocklevel;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
  
}
