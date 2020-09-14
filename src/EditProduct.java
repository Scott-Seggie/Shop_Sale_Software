
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
public class EditProduct extends JFrame implements ActionListener 
{

//ArrayList to fill with Product Objects
ArrayList<Product> products;    

DecimalFormat formatter;
    
//Menu Variable 
private JMenuBar Menu = new JMenuBar();   
private JMenu File = new JMenu("File");
private JMenu Add = new JMenu("Add");
private JMenu Edit = new JMenu("Edit");
private JMenu Retail = new JMenu("Retail");
private JMenuItem Login = new JMenuItem("Login");
private JMenuItem Exit = new JMenuItem("Exit");
private JMenuItem AddProduct = new JMenuItem("Add Product");
private JMenuItem EditProduct = new JMenuItem("Edit Product");
private JMenuItem EditStock = new JMenuItem("Edit Stock");
private JMenuItem Sell = new JMenuItem("Sell");


//panels 
private JPanel Top = new JPanel();
private JPanel Center = new JPanel();
private JPanel CenterT = new JPanel();
private JPanel Grid = new JPanel();
private JPanel East = new JPanel();
private JPanel West = new JPanel();
private JPanel Bottom = new JPanel();


//Lables, Fields, Button, ComboBox
private JLabel title = new JLabel(" Edit Product ");
private JLabel combo = new JLabel ("    Product List: ");

private JLabel title2 = new JLabel(" Reciept ");

private JLabel name = new JLabel (" Product Name: ");
private JLabel price = new JLabel ("  Product Price: ");
private JLabel stock = new JLabel ("     Stock Level: ");

private JComboBox <String> productList = new JComboBox();

private JTextField txtname = new JTextField(15);
private JTextField txtprice = new JTextField(15);
private JTextField txtstock = new JTextField(15);

private JButton save = new JButton("Save");
private JButton delete = new JButton("Delete");

//Icons from Image folder
ImageIcon fileIcon = new ImageIcon ("images/file.png");
ImageIcon loginIcon = new ImageIcon ("images/login.png");
ImageIcon exitIcon = new ImageIcon ("images/exit.png");
ImageIcon addIcon = new ImageIcon ("images/add.png");
ImageIcon addProIcon = new ImageIcon ("images/addProduct.png");
ImageIcon editIcon = new ImageIcon ("images/edit.png");
ImageIcon editProIcon = new ImageIcon ("images/editProduct.png");
ImageIcon editStoIcon = new ImageIcon ("images/editStock.png");
ImageIcon retailIcon = new ImageIcon ("images/retail.png");
ImageIcon sellIcon = new ImageIcon ("images/sell.png");
ImageIcon saveIcon = new ImageIcon ("images/save.png");
ImageIcon deleteIcon = new ImageIcon ("images/delete.png");

/** Here I have created a method to save object information to a 
     * saved file "Products.dat" after the user has entered details of product.
     */
public void  saveProductsToFile()
    {
        try
        {
            FileOutputStream file = new FileOutputStream("Products.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            //for loop to increase ArrayList with each Objectthe user saves
            for (int i = 0; i<products.size(); i++)
            {
                outputFile.writeObject(products.get(i)); 
            }
            
            outputFile.close();
            
            JOptionPane.showMessageDialog(null, "Successfully saved!");
            
        }
        catch (IOException e)
        {
           JOptionPane.showMessageDialog(null, e.getMessage()); 
        }
    }



/** Here I have created a method to get the ArrayList information from my 
     * saved file "Products.dat" and populate the array list. 
     */
 public void populateArrayList()
    {
        
       try
       {
           //opening file to be used
          FileInputStream file = new FileInputStream("Products.dat");
          //reading from the input stream 
          ObjectInputStream inputFile = new ObjectInputStream(file);
          
          /*checking for the end of the file
           (start on false as when you start readin it wont be the end)*/
          boolean endOfFile = false;
          /*while loop to make sure while its not the end of the file data
          continues to be read/*
          */
          while (!endOfFile)
          {
             try
             {
                products.add((Product)inputFile.readObject());
             }
             // when end of file happens change endOFfile to true
             catch (EOFException e)
             {
               endOfFile = true; 
             }
             catch (Exception f)
             {
               JOptionPane.showMessageDialog(null, f.getMessage());  
             }
          }
          
          inputFile.close();
       }
       catch (IOException e)
       {
          JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }
 
 //Delete method, same as the save just with a different success message. 
     public void  DeleteProductsFromFile()
    {
     /*Warning asking the user if they are sure they want to delete the product
        I have then used a If statement so if they select yes the file will be 
        deleted and if no the warning will close the the product will remain. 
        */
        int dialogButton =JOptionPane.YES_NO_OPTION;
        
        int dialogResult = JOptionPane.showConfirmDialog
         (null, "Are you sure you want to delete this product?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            
  
        try
        {
            FileOutputStream file = new FileOutputStream("Products.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            for (int i = 0; i<products.size(); i++)
            {
                outputFile.writeObject(products.get(i)); 
            }
            
            outputFile.close();
            this.dispose();
            
            JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            
            new EditProduct().setVisible(true);
        }
        catch (IOException e)
        {
           JOptionPane.showMessageDialog(null, e.getMessage()); 
        }
    }
        else if(dialogResult==JOptionPane.NO_OPTION)
        {
         this.dispose();
         new EditProduct().setVisible(true);
        }
    }

    
public EditProduct()
{
    
 //allows the user to use the update button using the enter key
    getRootPane().setDefaultButton(save);
    
 //setting the formate for the value sent accross to price 
    formatter = new DecimalFormat("#,###.00");
    
 //Initializing the ArrayLists
    products = new ArrayList<Product>();
   
 /*Calling method I created to populate the ArrayList with saved Products
    from a file. */
    populateArrayList();   
        
 //populating the combo box from the ArrayList       
    String [] productsArray = new String[products.size()];
       
      for (int i =0; i<products.size(); i++)
     
      productsArray[i] = products.get(i).getName() + " | £" + 
                 formatter.format(products.get(i).getPrice());
     
       
    productList.setModel(new javax.swing.DefaultComboBoxModel<>(productsArray));
       
    productList.setSelectedIndex(0);
    
    
 //Frame
    setTitle("U1629227");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 540);
    setLocationRelativeTo(null);
    setResizable(false);
    
    //Menu Bar
    setJMenuBar(Menu);
    Menu.setBackground(Color.WHITE);
    
    Menu.add(File);
    Menu.add(Add);
    Menu.add(Edit);
    Menu.add(Retail);
    
    File.add(Login);
    File.add(Exit); 
    
    Add.add(AddProduct);
    
    Edit.add(EditProduct);
    Edit.add(EditStock);
    
    Retail.add(Sell);
    
    File.setIcon(fileIcon);
    Login.setIcon(loginIcon);
    Exit.setIcon(exitIcon);
    
    Add.setIcon(addIcon);
    AddProduct.setIcon(addProIcon);
    
    Edit.setIcon(editIcon);
    EditProduct.setIcon(editProIcon);
    EditStock.setIcon(editStoIcon);
    
    Retail.setIcon(retailIcon);
    Sell.setIcon(sellIcon);
    
    //Listeners 
    Login.addActionListener(this);
    Exit.addActionListener(this);
    AddProduct.addActionListener(this);
    EditProduct.addActionListener(this);
    EditStock.addActionListener(this);
    Sell.addActionListener(this);
    save.addActionListener(this);
    delete.addActionListener(this);
    productList.addActionListener(this);
    
    //North Panel 
    add (BorderLayout.NORTH, Top);
    Top.setPreferredSize(new Dimension(800,40));
    Top.setBackground(new Color (90,163,206));
    
    
    //West Panel 
    add (BorderLayout.WEST, West);
    West.setPreferredSize(new Dimension(100,500));
    West.setBackground(new Color (90,163,206));
    
    //East Panel
    add (BorderLayout.EAST, East);
    East.setPreferredSize(new Dimension(100,500));
    East.setBackground(new Color (90,163,206));
    
    //Center Panel
    add (BorderLayout.CENTER, Center);
    Center.setPreferredSize(new Dimension(600,500));
    Center.setBackground(new Color (90,163,206));
    Center.add(BorderLayout.NORTH,CenterT);
    CenterT.setPreferredSize(new Dimension(600,100));
    CenterT.setBackground(new Color (90,163,206));
    CenterT.add(title);
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD + Font.ITALIC, 36));
    // I have switched to a GridLayout here for easy of design.
    Center.add(BorderLayout.CENTER,Grid);
    Grid.setLayout(new GridLayout(0,2,0,25));
    Grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    Grid.setBackground(new Color (90,163,206));
    Grid.add(combo);
    combo.setForeground(Color.WHITE);
    combo.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD, 18));
    Grid.add(productList);
    productList.setBackground(new Color (255,255,255));
    Grid.add(name);
    name.setForeground(Color.WHITE);
    name.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD, 18));
    Grid.add(txtname);
    txtname.setFont(new Font("Arial Rounded MT Bold",
            Font.PLAIN, 14));
    Grid.add(price);
    price.setForeground(Color.WHITE);
    price.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD, 18));
    Grid.add(txtprice);
    txtprice.setFont(new Font("Arial Rounded MT Bold",
            Font.PLAIN, 14));
    Grid.add(stock);
    stock.setForeground(Color.WHITE);
    stock.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD, 18));
    Grid.add(txtstock);
    txtstock.setFont(new Font("Arial Rounded MT Bold",
            Font.PLAIN, 14));
    
    //Bottom Panel 
    add(BorderLayout.SOUTH,Bottom);
    Bottom.setPreferredSize(new Dimension(600,80));
    Bottom.setBackground(new Color (90,163,206));
    Bottom.add(save);
    save.setBackground(new Color (255,255,255));
    save.setIcon(saveIcon);
    save.setFont(new Font("Arial",
            Font.PLAIN,14));
    Bottom.add(delete);
    delete.setBackground(new Color (255,255,255));
    delete.setIcon(deleteIcon);
    delete.setFont(new Font("Arial",
            Font.PLAIN,14));
    
    //setting this Frame to visible 
    setVisible(true);
    
    
    
    
    
}
    
public void actionPerformed(ActionEvent e)
{
  if (e.getSource()== Exit)
{
   System.exit(0);
} 
  
  if (e.getSource ()==Login)
{
    new LoginGUI().setVisible(true);
    this.dispose();
}
  
  if (e.getSource()== AddProduct)
{
   new AddNewProduct().setVisible(true);
   this.dispose();  
} 
  
  if (e.getSource()== EditProduct)
{
   new EditProduct().setVisible(true);
   this.dispose();
} 
  
  if (e.getSource()== EditStock)
{
   new EditStock().setVisible(true);
   this.dispose(); 
}
  
  if (e.getSource()== Sell)
{
   new Sell().setVisible(true);
   this.dispose();
}
  
  if (e.getSource()==productList)
{
       int selectedIndex = productList.getSelectedIndex();
       
       txtname.setText(products.get(selectedIndex).getName());
       txtprice.setText(products.get(selectedIndex).getPrice()+"");
       txtstock.setText(products.get(selectedIndex).getStocklevel()+"");     
}
  
  if (e.getSource()==save)
{
   if (txtname.getText().isEmpty() || txtprice.getText().isEmpty()|| txtstock.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter all fields!");
        }
        else 
        {
           int selectedIndex = productList.getSelectedIndex();
           products.get(selectedIndex).setName(txtname.getText().trim());
           products.get(selectedIndex).setPrice(Double.parseDouble(txtprice.getText().trim()));
           products.get(selectedIndex).setStocklevel(Integer.parseInt(txtstock.getText().trim()));
           
           saveProductsToFile();
        } 
}
 if (e.getSource()==delete) 
 {
     int selectedIndex = productList.getSelectedIndex();
       
        products.remove(selectedIndex);
        
        DeleteProductsFromFile();
 }
  
  
  
}     
}
