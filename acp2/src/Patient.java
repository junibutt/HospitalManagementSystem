import javax.swing.*;
import javax.swing.event.ListDataEvent;

import org.apache.commons.lang.time.DateUtils;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.awt.*;

public class Patient extends JFrame implements ActionListener {
   JLabel pnl,pfl,dnl,dhl,pl,gl;
   JTextField tfpn,tffn;
   JTextArea tapre ,tadh;
   JRadioButton rg1,rg2 ;
   ButtonGroup bg;
   JComboBox cbdn,sList ;
   JDateChooser chooser ;

   private JButton  save,cancel;
   private JFrame mainf,sframe ; 

    
public void addPatient(){

pnl=new JLabel("Patient Name");
pfl=new JLabel("Patient Father Name");
dnl=new JLabel("Patient Doctor Name");
pl=new JLabel("Patient Prescription");
gl=new JLabel("Patient Gender");
dhl=new JLabel("Patient Disease history");
tfpn = new JTextField();//patient name
tffn = new JTextField();//father name
tapre = new JTextArea();//prescription
tadh = new JTextArea();//disease history
rg1 = new JRadioButton("Male");// gender
rg2 = new JRadioButton("Female");// gender
bg= new ButtonGroup();       
chooser = new JDateChooser();
java.util.Date date = new java.util.Date();
chooser.setDate(date);
JPanel panelDate = new JPanel();
panelDate.add(new JLabel("Date of Birth:"));
panelDate.add(chooser);
bg.add(rg1);
bg.add(rg2);
cbdn = new JComboBox();//doctor name
JPanel bpanel= new JPanel(new GridLayout(1,2));
bpanel.add(rg1);
bpanel.add(rg2);
JPanel formPanel =new JPanel();
formPanel.setLayout(new GridLayout(14,1,1,1));
formPanel.add(pnl);
formPanel.add(tfpn);
formPanel.add(pfl);
formPanel.add(tffn);
formPanel.add(dnl);
formPanel.add(cbdn);
formPanel.add(gl);
formPanel.add(bpanel);
formPanel.add(panelDate);
formPanel.add(dhl);
formPanel.add(tadh);
formPanel.add(pl);
formPanel.add(tapre);
JPanel formPanel2 =new JPanel();
formPanel2.setLayout(new GridLayout(1,2,5,5));
save =new JButton("Save ");
cancel =new JButton("Cancel");
formPanel2.add(save);
cancel.addActionListener(this);
save.addActionListener(this);
formPanel2.add(cancel);
cancel.addActionListener(this);

mainf = new JFrame(); 
mainf.setLayout(new BorderLayout());
mainf.add(formPanel,BorderLayout.NORTH);
mainf.add(formPanel2,BorderLayout.SOUTH);

mainf.setSize(800,800);
mainf.setVisible(true);






}



public void showButton(String  bName,String type)
{
    try
    { 
    Connection con = DB.connection();
    Statement stat= con.createStatement();
    String query="select * from Patient";
    ResultSet rs = stat.executeQuery(query);
    String[] data= new String[100];
    int index=-1;
    while(rs.next())
    {
        index++;
        data[index]=rs.getString(type);
    }
    sList= new JComboBox<>(data);
    JButton jbt =new  JButton(bName);
    JButton jbt2 = new  JButton("Cancel");
    jbt2.addActionListener(this);
    mainf = new JFrame();
    JPanel container =new JPanel();
    container.add(sList);
    container.add(jbt);
    container.add(jbt2);
    jbt.addActionListener(this);
    mainf.add(container);
    mainf.setSize(200,300);
    mainf.show();
    
}
catch (Exception ex)
{
    JOptionPane.showMessageDialog(null, "Try again");
}
}

public void showSearchData(String id,String n,String fn,String dn,String pp,String pg,String pdh,boolean editAble)
{
    Font mainfont=new Font("abc",Font.BOLD,15);
    JLabel pnl1,pfl1,dobl1,dnl1,gl1,pid1,pid2;
    pid1=new JLabel("Patient ID :");pid1.setFont(mainfont);
    pnl=new JLabel("Patient Name :");pnl.setFont(mainfont);
    pfl=new JLabel("Patient Father Name :");pfl.setFont(mainfont);
    dobl=new JLabel("Patient Date Of Birth :");dobl.setFont(mainfont);
    dnl=new JLabel("Patient Doctor Name :");dnl.setFont(mainfont);
    pl=new JLabel("Patient Prescription :");pl.setFont(mainfont);
    gl=new JLabel("Patient Gender :");gl.setFont(mainfont);
    dhl=new JLabel("Patient Disease history :");dhl.setFont(mainfont);
    

    pid2=new JLabel(id);
    pnl1=new JLabel(n);
    pfl1=new JLabel(fn);

    //dobl=new JLabel(db);
    dnl1=new JLabel(dn);
    gl1=new JLabel(pg);
    tapre=new JTextArea(pp);
    tadh= new JTextArea(pdh);
    if(editAble==false)
    {
        tapre.setEditable(false);
        tadh.setEditable(false);
    }
    JPanel formPanel =new JPanel();
    formPanel.setLayout(new GridLayout(7,2,1,1));
    formPanel.add(pid1);
    formPanel.add(pid2);
    formPanel.add(pnl);
    formPanel.add(pnl1);
    formPanel.add(pfl);
    formPanel.add(pfl1);
    formPanel.add(dnl);
    formPanel.add(dnl1);
    formPanel.add(gl);
    formPanel.add(gl1);
    formPanel.add(dhl);
    formPanel.add(tapre);
    formPanel.add(pl);
    formPanel.add(tadh);
    JButton jbt2 = new  JButton("Cancel");
    jbt2.addActionListener(this);
    //formPanel.add(dobl);
    sframe = new JFrame(); 
    sframe.setLayout(new BorderLayout());
    sframe.add(formPanel,BorderLayout.NORTH);
    JPanel panel2= new JPanel();
    if(editAble==true)
    {
        JButton update =new JButton("edit");
        update.addActionListener(this);
        panel2.add(update);
        sframe.add(panel2);
    }
    sframe.setSize(800,800);
    sframe.setVisible(true);
}

void searchData(String type,boolean editAble)
{
    try
        { 
        Connection con = DB.connection();
        Statement stat= con.createStatement();
        String query="select * from Patient where "+ type+ "='"+sList.getSelectedItem()+"'";
        ResultSet rs = stat.executeQuery(query);
        while(rs.next())
        {
           System.out.println( rs.getString(type));
           showSearchData(rs.getString("p_id"),rs.getString("p_name"),rs.getString("pf_name"),rs.getString("d_id"),rs.getString("prescription"),rs.getString("p_gender"),rs.getString("d_histroy"),editAble);
        }
    }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"");
        }

}

@Override
public void actionPerformed(ActionEvent ae) {
    String button= ae.getActionCommand();
    if(ae.getSource()==save)
    {
        String gend="";
        if(rg1.isSelected())
        {
            gend="MALE";
        }
        if(rg1.isSelected())
        {
            gend="FEMALE";
        }
       
        SimpleDateFormat df=new SimpleDateFormat("mm/dd/yyyy");
		String date=(String)(df.format(chooser.getDate()));
        try{
            Connection con =DB.connection() ;
            String query = "insert into Patient (p_name,pf_name,p_dob,d_histroy,prescription,p_gender) values(?,?,?,?,?,?)";
            PreparedStatement stat= con.prepareStatement(query);
            stat.setString(1, tfpn.getText());
            stat.setString(2, tffn.getText());
            stat.setString(3, date.toString());
            stat.setString(4, tadh.getText());
            stat.setString(5, tapre.getText());
            stat.setString(6, gend);
            //stat.setString(5, cbdn.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Saved");
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        mainf.dispose();
    }
    else if (button=="Cancel")
    {
        System.out.println("Disposed");
        mainf.dispose();
    } 
    else if(button=="search by id")
    {
        searchData("p_id",false);
    }
    else if(button=="search by name")
    {
        searchData("p_name",false);
    }
    else if(button=="search by doctor")
    {
        searchData("d_id",false);
    }
    else if (button=="update")
    {
        searchData("p_id",true);
    }
    else if (button=="edit")
    {
        try{
            Connection con =DB.connection() ;
            Statement st = con.createStatement();
            String query = "update Patient set prescription='"+tapre.getText()+"', d_histroy='"+tadh.getText()+"' where p_id='"+sList.getSelectedItem()+"'";
            st.execute(query);
            st.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Try again");
        }
        mainf.dispose();
    }
    else if(button=="delete")
    {
        try{
            Connection con =DB.connection() ;
            Statement st = con.createStatement();
            String query = "delete from Patient where p_id='"+sList.getSelectedItem()+"'";
            st.execute(query);
            st.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}




}