
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
public class AddNewProduct extends JFrame implements ActionListener
{

//ArrayList to fill with Product Objects
ArrayList<Product> products;    
    
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


//Lables, Fields, Button
private JLabel title = new JLabel(" Add New Products ");
private JLabel name = new JLabel ("Product Name: ");
private JLabel price = new JLabel ("Product Price: ");
private JLabel stock = new JLabel ("    Stock Level: ");

private JTextField txtname = new JTextField(15);
private JTextField txtprice = new JTextField(15);
private JTextField txtstock = new JTextField(15);

private JButton add = new JButton("Add Product");


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

    
public AddNewProduct()
{
    
 //allows the user to use the update button using the enter key
    getRootPane().setDefaultButton(add);
    
 //Initializing the ArrayLists
    products = new ArrayList<Product>();
   
 /*Calling method I created to populate the ArrayList with saved Products
    from a file. */
        populateArrayList();   
    
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
    add.addActionListener(this);
    
    
    //North Panel 
    add (BorderLayout.NORTH, Top);
    Top.setPreferredSize(new Dimension(800,60));
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
    Center.add(BorderLayout.CENTER,Grid);
    Grid.setLayout(new GridLayout(0,2,0,25));
    Grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    Grid.setBackground(new Color (90,163,206));
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
    add(BorderLayout.SOUTH,Bottom);
    Bottom.setPreferredSize(new Dimension(600,100));
    Bottom.setBackground(new Color (90,163,206));
    Bottom.add(add);
    add.setBackground(new Color (255,255,255));
    add.setIcon(saveIcon);
    add.setFont(new Font("Arial",
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
  
  if (e.getSource()==add)
{
    if (txtname.getText().isEmpty() || txtprice.getText().isEmpty()|| 
            txtstock.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter all fields!");
        }
        else 
        {
    /* here I am taking the information entered by the user in the text fields
     * and adding them to the ArrayList. At the same time I am converting from 
     * String to Integer and Double. 
     */
            Product product = new Product (txtname.getText().trim()
                    ,Integer.parseInt(txtstock.getText().trim())
                    ,Double.parseDouble(txtprice.getText().trim()));
    
            products.add(product);
            
           //Calling method to save new product to file.
            saveProductsToFile();
        } 
        
}
}     
}



