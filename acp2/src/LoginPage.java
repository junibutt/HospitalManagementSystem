import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPage extends JFrame implements ActionListener 
{
    JComboBox jcb ;
    JLabel userNameLable,passLable,loginLable;
    JTextField userName;
    JPasswordField pass;
    JButton loginButton;
    JPanel jp;

    JFrame jf ;
    LoginPage()
    {
        jf = new JFrame();
        Container c = getContentPane();
        this.setValues();
        this.setPanel();
        c.add(jp);
        jf.add(c);
        loginButton.addActionListener(this);
        jf.setSize(600,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    private void setValues()

    {
        Font mainfont=new Font("abc",Font.BOLD,14);
        loginLable= new JLabel("Login Page");
        loginLable.setFont(mainfont);

        userNameLable= new JLabel("User Name");
        userNameLable.setFont(mainfont);

        userName =new JTextField(20);
    
        passLable = new JLabel(" Password");
        passLable.setFont(mainfont);

        pass= new JPasswordField(20);

        jcb = new JComboBox(user.values());

        loginButton = new JButton("Login");  
   
    }
    
    private void setPanel()
    {
        jp= new JPanel();
        jp.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets= new Insets(10,0,0,0);
        c1.anchor=GridBagConstraints.LINE_START;
        c1.gridx=0;c1.gridy=0;jp.add(loginLable,c1);
        c1.gridx=0;c1.gridy=1;jp.add(userNameLable,c1);
        c1.gridx=1;c1.gridy=1;jp.add(userName,c1);
        c1.gridx=0;c1.gridy=2;jp.add(passLable,c1);
        c1.gridx=1;c1.gridy=2;jp.add(pass,c1);
        c1.gridx=2;c1.gridy=3;jp.add(jcb,c1);
        c1.gridx=1;c1.gridy=4;jp.add(loginButton,c1);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==loginButton)
        {
            
            String id=userName.getText();
            String pas= pass.getText();
            try
            { 
            Connection con =DB.connection() ;
            
            Statement st = con.createStatement();
            String query = "select pass from Login where user_name = '"+userName.getText()+"'";
            ResultSet rs = st.executeQuery(query); 
            
            String getpass="";
            while(rs.next())
            {
                getpass=rs.getString(1);
                System.out.println(getpass);;
            }
            if(getpass.equals(pas)&& id.equals(jcb.getSelectedItem().toString()))
            {
                JOptionPane.showMessageDialog(null, " Login Successfully");
                if(id.equals("admin"))
                {
                adminPage ap=new adminPage();
                jf.dispose();
                }
                else
                {
                GuestPage gp = new GuestPage();
                jf.dispose();
                }
            }
            else 
            {
                JOptionPane.showMessageDialog(null, " Try again");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        }
        

           /*  if((id.equals("admin"))&&(pas.equals("123"))&&(jcb.getSelectedItem()==user.admin))
            {
                JOptionPane.showMessageDialog(null, " Admin Login Successfully");
            }
            else if((id.equals("guest"))&&(pas.equals("123"))&&(jcb.getSelectedItem()==user.guest))
            {
                JOptionPane.showMessageDialog(null, "  Guest Login Successfully");
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "  Invalid Login try again ");
            }*/
        }
}

enum user
    {
        admin,guest
    }