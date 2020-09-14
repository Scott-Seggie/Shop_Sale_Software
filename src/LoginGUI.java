
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class LoginGUI extends JFrame implements ActionListener
{
      
//Menu Varibales     
private JMenuBar Menu = new JMenuBar();    
private JMenu File = new JMenu("File");
private JMenuItem Exit = new JMenuItem("Exit");

// left Panels
private JPanel leftPanel = new JPanel();
private JPanel leftTop = new JPanel();
private JPanel leftCenter = new JPanel();

// right Panels 
private JPanel rightPanel = new JPanel();
private JPanel rightTop = new JPanel();
private JPanel rightCenter = new JPanel();
private JPanel rightBottom = new JPanel();
private JPanel rightCenterTop = new JPanel();
private JPanel rightCenterMid =new JPanel();
private JPanel rightCenterBot =new JPanel();
private JPanel rightBottomTop =new JPanel();
private JPanel rightBottomMid =new JPanel();


//labels, Fields and button
private JLabel title = new JLabel("Welcome to eShop");
private JLabel eShop = new JLabel();
private JLabel user = new JLabel ("Username");
private JLabel pass = new JLabel ("Password");
private JTextField username = new JTextField(20);
private JPasswordField password = new JPasswordField(18);
private JButton login = new JButton("Login");

//Icons from Image folder
ImageIcon fileIcon = new ImageIcon ("images/file.png");
ImageIcon exitIcon = new ImageIcon ("images/exit.png");
ImageIcon eShopIcon = new ImageIcon ("images/Main.png");
ImageIcon userIcon = new ImageIcon ("images/user.png");
ImageIcon passIcon = new ImageIcon ("images/password.png");
    
public LoginGUI()
{
    //login button works with Enter Key 
    getRootPane().setDefaultButton(login);
    
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
    File.add(Exit); 
    
    //Menu Icons
    File.setIcon(fileIcon);
    Exit.setIcon(exitIcon);
    
    //Button Listeners
    Exit.addActionListener(this);
    login.addActionListener(this);
    
    
  //Left Panel 
    add (BorderLayout.WEST, leftPanel);
    leftPanel.setPreferredSize(new Dimension(400,540));
    leftPanel.setBackground(new Color (90,163,206));
    leftPanel.add(BorderLayout.NORTH,leftTop);
    leftTop.setPreferredSize(new Dimension(400,80));
    leftTop.setBackground(new Color (90,163,206));
    leftPanel.add(BorderLayout.CENTER,leftCenter);
    leftCenter.setPreferredSize(new Dimension(400,460));
    leftCenter.setBackground(new Color (90,163,206));
    leftCenter.add(title);
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Elephant", Font.BOLD + Font.ITALIC, 34));
    leftCenter.add(eShop);
    eShop.setIcon(eShopIcon);
    
    
    //Right Panel 
    add (BorderLayout.EAST, rightPanel);
    rightPanel.setPreferredSize(new Dimension(400,540));
    rightPanel.setBackground(new Color (255,255,255));
    rightPanel.add(BorderLayout.NORTH,rightTop);
    rightTop.setPreferredSize(new Dimension(400,80));
    rightTop.setBackground(new Color (255,255,255));
    rightPanel.add(BorderLayout.CENTER,rightCenter);
    rightCenter.setPreferredSize(new Dimension(400,200));
    rightCenter.setBackground(new Color (255,255,255));
    rightPanel.add(BorderLayout.SOUTH,rightBottom);
    rightBottom.setPreferredSize(new Dimension(400,180));
    rightBottom.setBackground(new Color (255,255,255));
    rightCenter.add(BorderLayout.NORTH,rightCenterTop);
    rightCenterTop.setBackground(new Color (255,255,255));
    rightCenterTop.setPreferredSize(new Dimension(400,60));
    rightCenterTop.add(user);
    user.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,14));
    user.setIcon(userIcon);
    rightCenter.add(BorderLayout.CENTER,rightCenterMid);
    rightCenterMid.add(username);
    rightCenterMid.setBackground(new Color (255,255,255));
    rightCenterMid.setPreferredSize(new Dimension(400,60));
    username.setFont(new Font("Arial",Font.PLAIN,14));
    rightCenter.add(BorderLayout.CENTER,rightCenterBot);
    rightCenterBot.setBackground(new Color (255,255,255));
    rightCenterBot.setPreferredSize(new Dimension(400,60));
    rightCenterBot.add(pass);
    pass.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,14));
    pass.setIcon(passIcon);
    rightBottom.add(BorderLayout.NORTH,rightBottomTop);
    rightBottomTop.setPreferredSize(new Dimension(400,60));
    rightBottomTop.setBackground(new Color (255,255,255));
    rightBottomTop.add(password);
    password.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,14));
    rightBottom.add(BorderLayout.CENTER,rightBottomMid);
    rightBottomMid.setPreferredSize(new Dimension(400,50));
    rightBottomMid.setBackground(new Color (255,255,255));
    rightBottomMid.add(login);
    login.setBackground(new Color (90,163,206));
    login.setForeground(new Color (255,255,255));
    login.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,14));
   
    
    //setting this Frame to visible 
    setVisible(true);
}

public void actionPerformed(ActionEvent e)
{
  if (e.getSource()== Exit)
{
   System.exit(0);
}
 if (e.getSource()== login)
{
    if(username.getText().equals("eShop") 
            && password.getText().equals("password"))
      {
          new AddNewProduct().setVisible(true);
          this.dispose();
      }
      else
      {
           JOptionPane.showMessageDialog
        (null, "Please enter valid username and password!");
      }
}
     
}
}