import javax.swing.*;
import javax.swing.event.ListDataEvent;

import com.toedter.calendar.JCalendar;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;

public class Doctor extends JFrame implements ActionListener
{
     JButton save, cancel;
     JTextField tdna=new JTextField();
     JComboBox cdid=new JComboBox<>();
     JFrame fd;

    public void addoctor(){
        JLabel Dna,did;
        fd= new JFrame();
        
        Dna=new JLabel("Doctor Name");
        did=new JLabel("Diesease specialist");
        tdna=new JTextField();
        cdid=new JComboBox<>();
        JPanel pd=new JPanel();
        pd.setLayout(new GridLayout(4,1,1,1));
        pd.add(Dna);
        pd.add(tdna);
        pd.add(did);
        pd.add(cdid);
        JPanel pd2=new JPanel();
        pd2.setLayout(new GridLayout(2,1,1,1));
        save=new JButton("save");
        save.addActionListener(this);
        cancel=new JButton("Cancel");
        cancel.addActionListener(this);
        pd2.add(save);
        pd2.add(cancel);
        fd.setLayout(new BorderLayout());
        fd.add(pd,BorderLayout.NORTH);
        fd.add(pd2,BorderLayout.SOUTH);
        fd.setSize(500,500);
        fd.setVisible(true);


        }
        @Override
    public void actionPerformed(ActionEvent ae) {
            String button= ae.getActionCommand();
            if(ae.getSource()==save)
            {
                try{
                    Connection con =DB.connection() ;
                    Statement st = con.createStatement();
                    String query = "insert into Doctor (doctor_name,disease_id) values(?,?)";
                    PreparedStatement stat= con.prepareStatement(query);
                    System.out.println(tdna.getText());
                    stat.setString(1, tdna.getText());
                    stat.setString(2,(String) cdid.getSelectedItem());
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
                fd.dispose();
            } 


            
        }



}       
    


