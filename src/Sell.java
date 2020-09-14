
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class Sell extends JFrame implements ActionListener 
{
    
//Varibales to calculate the subtotal and total and to add tax
double rPrice = 0.00;
double subtotal = 0;
double total = 0;
double tax = 1.2;
    
    
//Variables for generating OrderNumber
int refno;
String OrderRefNo = "C";
     
     
    
ArrayList<Product> products;

//Deciimal Formatter to allow me to set how my doubles variables appear.
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


//Panles
private JPanel Top = new JPanel();
private JPanel CenterE = new JPanel();
private JPanel WCenter = new JPanel();
private JPanel WWCenter = new JPanel();
private JPanel Grid = new JPanel();
private JPanel Grid2 = new JPanel();
private JPanel East = new JPanel();
private JPanel West = new JPanel();
private JPanel WBottom = new JPanel();


private JLabel title = new JLabel(" Sell Stock ");
private JLabel title2 = new JLabel(" Reciept ");
private JLabel combo = new JLabel ("      Product List: ");
private JLabel stock = new JLabel ("       Stock Level: ");
private JLabel quantity = new JLabel (" Select Quantity: ");
private JLabel sub = new JLabel ("            Subtotal: ");
private JLabel lbtotal = new JLabel ("                 Total: ");



private JComboBox productList = new JComboBox();

private JTextField txtstock = new JTextField(15);
private JTextField txtquant = new JTextField(15);
private JTextField txtsub = new JTextField(15);
private JTextField txtTotal = new JTextField(15);

private JTextArea txtReciept = new JTextArea(17,26);
private JTextArea txtRef = new JTextArea(6,26);

private JButton add = new JButton("Add");
private JButton btnTotal = new JButton("Total");
private JButton process = new JButton("Process");


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
ImageIcon sellStockIcon = new ImageIcon ("images/sellStock.png");
ImageIcon processIcon = new ImageIcon ("images/process.png");
ImageIcon recieptIcon = new ImageIcon ("images/reciept.png");


//Method to populate ArrayList
    public void populateArrayList()
    {
       try
       {
          FileInputStream file = new FileInputStream("Products.dat");
          ObjectInputStream inputFile = new ObjectInputStream(file);
          
          boolean endOfFile = false;
          while (!endOfFile)
          {
             try
             {
                products.add((Product)inputFile.readObject());
             }
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
    
    
     // Save method, saves the product to file.
     public void  saveProductsToFile()
    {
        try
        {
            FileOutputStream file = new FileOutputStream("Products.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
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
     
      public void  ItemsAdded()
    {
        try
        {
            FileOutputStream file = new FileOutputStream("Products.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
            for (int i = 0; i<products.size(); i++)
            {
                outputFile.writeObject(products.get(i)); 
            }
            
            outputFile.close();
            
            JOptionPane.showMessageDialog(null,
                    "              Items Sold"+"\n"+ "         Oreder ref: "+OrderRefNo);
            this.dispose();
            new Sell().setVisible(true);
            
        }
        catch (IOException e)
        {
           JOptionPane.showMessageDialog(null, e.getMessage()); 
        }
    }


public Sell()
{
   //Allows the user to press the update button using the enter key.
        getRootPane().setDefaultButton(add);
        
      formatter = new DecimalFormat("#,###.##");
      
      /**Starting this button off to stop the user from making order without
       * using total first or selecting an item. 
       */
       process.setEnabled(false); 
       btnTotal.setEnabled(false); 
      
     
      products = new ArrayList<Product>();
      //Calling method to populate array list, Which Im and using to fill jCombobox1. 
      populateArrayList();
   
      String [] productsArray = new String[products.size()];
       
       for (int i =0; i<products.size(); i++)
       {
           productsArray[i] = products.get(i).getName() + " | £" + 
                   formatter.format(products.get(i).getPrice());
       }
       
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
    productList.addActionListener(this);
    add.addActionListener(this);
    btnTotal.addActionListener(this);
    process.addActionListener(this);
    
    
    //left panel
    add (BorderLayout.WEST, West);
    West.setPreferredSize(new Dimension(400,540));
    West.setBackground(new Color (90,163,206));
    West.add(title);
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD + Font.ITALIC, 30));
    title.setIcon(sellStockIcon);
    title.setHorizontalTextPosition(SwingConstants.LEFT);
    West.add(BorderLayout.NORTH,Top);
    Top.setPreferredSize(new Dimension(400,140));
    Top.setBackground(new Color (90,163,206));
    // I have switched to a GridLayout here for easy of design.
    Top.add(BorderLayout.NORTH,Grid);
    Grid.setPreferredSize(new Dimension(360,100));
    Grid.setLayout(new GridLayout(0,2,0,15));
    Grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    Grid.setBackground(new Color (90,163,206));
    Grid.add(combo);
    combo.setForeground(Color.WHITE);
    combo.setFont(new Font("Arial Rounded MT Bold",Font.BOLD, 18));
    Grid.add(productList);
    productList.setBackground(new Color (255,255,255));
    Grid.add(stock);
    stock.setForeground(Color.WHITE);
    stock.setFont(new Font("Arial Rounded MT Bold",Font.BOLD, 18));
    Grid.add(txtstock);
    txtstock.setEditable(false);
    txtstock.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    Grid.add(quantity);
    quantity.setForeground(Color.WHITE);
    quantity.setFont(new Font("Arial Rounded MT Bold",Font.BOLD, 18));
    Grid.add(txtquant);
    txtquant.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    West.add(add);
    add.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    add.setBackground(new Color (255,255,255));
    West.add(btnTotal);
    btnTotal.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    btnTotal.setBackground(new Color (255,255,255));
    West.add (BorderLayout.CENTER, WWCenter);
    WWCenter.setPreferredSize(new Dimension(400,40));
    WWCenter.setBackground(new Color (90,163,206));
    West.add (BorderLayout.SOUTH, WBottom);
    WBottom.setPreferredSize(new Dimension(400,88));
    WBottom.setBackground(new Color (90,163,206));
    WBottom.add(BorderLayout.NORTH,Grid2);
    Grid2.setBackground(new Color (90,163,206));
    Grid2.setPreferredSize(new Dimension(360,62));
    Grid2.setLayout(new GridLayout(0,2,0,15));
    Grid2.add(sub);
    sub.setForeground(Color.WHITE);
    sub.setFont(new Font("Arial Rounded MT Bold",Font.BOLD, 18));
    Grid2.add(txtsub);
    txtsub.setEditable(false);
    txtsub.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    Grid2.add(lbtotal);
    lbtotal.setForeground(Color.WHITE);
    lbtotal.setFont(new Font("Arial Rounded MT Bold",Font.BOLD, 18));
    Grid2.add(txtTotal);
    txtTotal.setEditable(false);
    txtTotal.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    West.add(process);
    process.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN, 14));
    process.setBackground(new Color (255,255,255));
    process.setIcon(processIcon);
    
    
    //East Panel
    add (BorderLayout.EAST, East);
    East.setPreferredSize(new Dimension(400,540));
    East.setBackground(new Color (90,163,206));
    East.add(title2);
    title2.setForeground(Color.WHITE);
    title2.setFont(new Font("Arial Rounded MT Bold",
            Font.BOLD + Font.ITALIC, 30));
    title2.setIcon(recieptIcon);
    title2.setHorizontalTextPosition(SwingConstants.LEFT);
    East.add (BorderLayout.CENTER, CenterE);
    CenterE.setBorder(BorderFactory.createLineBorder(Color.black));
    CenterE.setPreferredSize(new Dimension(305,372));
    CenterE.setBackground(new Color (255,255,255));
    CenterE.add(txtReciept);
    txtReciept.setEditable(false);
    txtReciept.setFont(new Font("Arial",Font.BOLD, 12));
    txtReciept.setText("\t\t\n\t     Selling Reciept ");
    txtReciept.setBorder(BorderFactory.createLineBorder(Color.black));
    CenterE.add(txtRef);
    txtRef.setEditable(false);
    txtRef.setFont(new Font("Arial",Font.BOLD, 12));
    txtRef.setBorder(BorderFactory.createLineBorder(Color.black));
  
    
    //setting this Frame to visible 
    setVisible(true);
}
    
public void actionPerformed(ActionEvent e)
{
  if (e.getSource()== Exit)
{
   System.exit(0);
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
      
      txtstock.setText(products.get(selectedIndex).getStocklevel()+"");
  }
  if (e.getSource()==add)
  {
    int selectedIndex = productList.getSelectedIndex();
        if (txtquant.getText().isEmpty())
        {
           JOptionPane.showMessageDialog(null, "Please enter quantity to add!");
        }
        
        else if ((Integer.parseInt(txtquant.getText()))>
                products.get(selectedIndex).getStocklevel())
        {
             
            JOptionPane.showMessageDialog
        (null, "There is not enough stock. Please change your order quantity.");
        }
        else 
        {
            
        // formatting double to 2 decimal places
         DecimalFormat df = new DecimalFormat("###.##");
         
         /*get the selected Product from array then run the sell method passing 
        it the quantity entered by the user(also converting to a Integer)*/
         products.get(selectedIndex)
                 .sell(Integer.parseInt(txtquant.getText().trim()));
         
         
         subtotal =  products.get(selectedIndex).getPrice()
                 *(Double.parseDouble(txtquant.getText().trim()));
         
         
        
         rPrice = Integer.parseInt(txtquant.getText().trim())
                 *products.get(selectedIndex).getPrice();
         
        
       
         txtReciept.append("\n\n  "+txtquant.getText().trim()+" "
                 + products.get(selectedIndex).getName()+ " @  £"
                 + products.get(selectedIndex).getPrice() + " = "
                 +"£"+ df.format(rPrice)+"\n");
          
           
          total = total + subtotal;  
          
          
          txtsub.setText("£"+ df.format(total));
          btnTotal.setEnabled(true);
          process.setEnabled(false); 
          txtRef.setText(null);
          OrderRefNo = "C";
        }       
  }
  
  if (e.getSource()==btnTotal)
  {
     DecimalFormat df = new DecimalFormat("###.##");
        txtTotal.setText("£"+df.format(total*tax));
        
      
        refno = 1325 + (int) (Math.random()*4238);
        OrderRefNo += refno +1325;
        
        txtRef.append("\n"+"  Order Ref: "+OrderRefNo + "\n\n"+"  Total: £"+df.format (total*tax) );
        //Stopping the using from generating more than on order number for 
        btnTotal.setEnabled(false); 
        process.setEnabled(true);  
  }
  
  if (e.getSource()==process)
  {
     ItemsAdded();
        
        try
        {
            FileWriter writer = new FileWriter("reciept.txt");
            try(BufferedWriter w = new BufferedWriter(writer)){
               txtReciept.write(w);
               txtRef.write(w);
            }
            txtReciept.requestFocus();
            txtRef.requestFocus();
        }
        catch (IOException r) {
            JOptionPane.showMessageDialog(null, r);
        }  
  }
}
  
}
