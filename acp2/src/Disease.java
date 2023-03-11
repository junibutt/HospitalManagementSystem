import javax.swing.*;
import javax.swing.event.ListDataEvent;

import com.toedter.calendar.JCalendar;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.Attributes.Name;
import java.awt.*;

public class Disease extends JFrame implements ActionListener
 {
    JFrame mf;

JButton save ,cancel;
JLabel dnl,ddl;    //disease name and disease description label
 JTextField jtdn; //text area for disease name
 JTextArea jtdd; //text area for disease description
    public  void addDisease() {
    dnl=new JLabel("Disease Name");
    ddl=new JLabel("Disease Description");
    jtdn=new JTextField();
    jtdd = new JTextArea();
    save=new JButton("Save");
    save.addActionListener(this);
    cancel=new JButton("Cancel");
    cancel.addActionListener(this);
    JPanel form1 = new JPanel();
    form1.setLayout( new GridLayout(4,1,4,4));
    JPanel form2 = new JPanel();
    form2.setLayout( new GridLayout(1,2,4,4));
    form1.add(dnl);
    form1.add(jtdn);
    form1.add(ddl);
    form1.add(jtdd);
    form2.add(save);
    form2.add(cancel);
    mf=new JFrame();
    
    mf.add(form1,BorderLayout.NORTH); 
    mf.add(form2,BorderLayout.SOUTH);
    mf.setVisible(true);
    mf.setSize(600,500);
}
    
@Override
public void actionPerformed(ActionEvent e) {
 String button = e.getActionCommand();
    // TODO Auto-generated method stub
    if(e.getSource().equals(save)){
     try{
         Connection con =DB.connection() ;
         Statement st = con.createStatement();
         String query = "insert into Disease (disease_name,description) values(?,?)";
         PreparedStatement stat= con.prepareStatement(query);
         stat.setString(1, jtdn.getText());
         stat.setString(2, jtdd.getText());
         stat.executeUpdate();
         JOptionPane.showMessageDialog(null, "Data Saved");
     }
     catch (Exception ex)
     {
         JOptionPane.showMessageDialog(null, ex);
     }

    }
    else if (button=="Cancel")
          {
             System.out.println("Disposed");
             mf.dispose();
         } 
    
 }



}
