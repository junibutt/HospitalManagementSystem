import java.awt.*;
import javax.swing.*;

import javax.swing.ImageIcon.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminPage implements ActionListener {
    private Disease disease= new Disease();
    private Patient p = new Patient();
    private Doctor d = new Doctor();
    private JMenuBar menu= new JMenuBar();
    private JToolBar toolBar = new JToolBar();
    private JMenu mRecord,sRecord,help;
    adminPage()
    {
        JFrame jf= new JFrame();
        jf.setLayout(new FlowLayout());
        this.setMenuBar();
        this.set_ToolBar();
        this.setMRecord_Item();
        this.setSRecordItems();
        this.setHelpItem();
        jf.setJMenuBar(menu);
        jf.add(toolBar);
        jf.setSize(600,300);
        jf.setVisible(true);
    }
    private void setMenuBar()
    {
        mRecord=new JMenu("Manage Record");
        sRecord=new JMenu("Search Record");
        help=new JMenu("Help");
        menu.add(mRecord);
        menu.add(sRecord);
        menu.add(help);
    }

    private void set_ToolBar()
    {
        JButton aPat,sPat,aDoc,print;
        sPat = new JButton("Search Patient");
        aPat=new JButton("Add Patient"); 
        aDoc=new JButton("Add Doctor"); 
        print=new JButton("Print"); 
        toolBar.add(aPat);
        toolBar.addSeparator();
        toolBar.add(sPat);
        toolBar.addSeparator();
        toolBar.add(aDoc);
        toolBar.addSeparator();
        toolBar.add(print);
        aPat.addActionListener(this);
        sPat.addActionListener(this);
        aDoc.addActionListener(this);

    }

    private void setMRecord_Item()
    {
        JMenuItem mItem,mItem1,mItem2,mItem3,up,ud;
        mItem=new JMenuItem("Add Patient");
        mItem1=new JMenuItem("Add Doctor");
        mItem2=new JMenuItem("Add Diseas");
        mItem3=new JMenuItem("Delete Patient");
        JMenu mItem4=new JMenu("Update Record");
        up=new JMenuItem("Update Patient");
        ud=new JMenuItem("Update Doctor");
        mItem.addActionListener(this);
        mItem1.addActionListener(this);
        mItem2.addActionListener(this);
        mItem3.addActionListener(this);
        up.addActionListener(this);
        mItem4.add(up);
        mItem4.add(ud);
        mRecord.add(mItem);
        mRecord.add(mItem1);
        mRecord.add(mItem2);
        mRecord.add(mItem3);
        mRecord.add(mItem4);

    }

    private void setSRecordItems()
    {
        JMenuItem pName,pId,pAge,pDisease,pDoct,dName,dDS;
        pName=new JMenuItem("Search Patient by  Name");
        pId=new JMenuItem("Search Patient by  ID");
        pAge=new JMenuItem("Search Patient by  Age");
        pDisease=new JMenuItem("Search Patient by  Disease");
        pDoct=new JMenuItem("Search Patient by  Doctor");
        dName=new JMenuItem("Search Doctor by  Name");
        dDS=new JMenuItem("Search Doctor by  Disease Specilization");
        sRecord.add(pName);
        sRecord.add(pId);
        sRecord.add(pAge);
        sRecord.add(pDisease);
        sRecord.add(pDoct);
        sRecord.add(dName);
        sRecord.add(dDS);
        pName.addActionListener(this);
        pId.addActionListener(this);
        pAge.addActionListener(this);
        pDisease.addActionListener(this);
        pDoct.addActionListener(this);
        pName.addActionListener(this);
        dDS.addActionListener(this);
    }
    
    private void setHelpItem()
    {
        JMenuItem au,cp;
        au=new JMenuItem("About Us");
        cp=new JMenuItem("Change Password");
        help.add(au);
        help.add(cp);
        cp.addActionListener(this);
    }
    

    private void changePassword()
    {
        String newPass;
        newPass=JOptionPane.showInputDialog(null, "Enter new admin password");
        try{
            Connection con =DB.connection() ;
            Statement st = con.createStatement();
            String query = "update Login set pass ='"+newPass+"'where id='1'";
            st.execute(query);
            st.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Try again");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String button=ae.getActionCommand();
        // TODO Auto-generated method stub
        if(button=="Add Patient")
        {
            p.addPatient();
        }
        else if(button=="Delete Patient")
        {
            p.showButton("delete","p_id");
        }
        else if(button=="Update Patient")
        {
            p.showButton("update","p_id");
        }
        else if(button=="Search Patient by  Name")
        {
            p.showButton("search by name","p_name");
        }
        else if((button=="Search Patient by  ID")|| (button =="Search Patient"))
        {
            p.showButton("search by id","p_id");
        }
        else if(button=="Search Patient by  Disease")
        {
            p.showButton("search by disease","d_histroy");
        }
        else if(button=="Search Patient by  Doctor")
        {
            p.showButton("search by doctor","d_id");
        }
        else if(button=="Add Doctor")
        {
            d.addoctor();
        }
        else if(button=="Add Diseas")
        {
            disease.addDisease();
        }
        else if(button=="Change Password")
        {
            changePassword();
             
        }
    }

}
