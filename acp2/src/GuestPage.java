import javax.swing.*;
import javax.swing.ImageIcon.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;


public class GuestPage implements ActionListener {
    private Patient p= new Patient();
    private JMenuBar menu=new JMenuBar();
    private JToolBar toolBar= new JToolBar();
    private JMenu sR,pR,help;
    public GuestPage()
    {
        JFrame jf = new JFrame("Guest Account");
        jf.setLayout(new FlowLayout());
        this.setToolBar();
        this.setMenu();
        this.set_sR_Item();
        this.set_Help_Items();
        jf.add(toolBar);
        jf.setJMenuBar(menu);
        jf.setSize(600,300);
        jf.setVisible(true);
    }
    
    private void setToolBar()
    {
        JButton sr,print;
        sr = new JButton("Search Record");
        print = new JButton("Print");
        toolBar.add(sr);
        toolBar.add(print);
        sr.addActionListener(this);
    }

    private void setMenu()
    {
        sR=new JMenu("Search Record");
        pR=new JMenu("Print Record");
        help=new JMenu("Help");
        menu.add(sR);
        menu.add(pR);
        menu.add(help);
    }

    private void set_sR_Item()
    {
        JMenuItem sN,id,age;
        sN=new JMenuItem("Search by Name");
        id=new JMenuItem("Search by ID");
        age=new JMenuItem("Search by Age");
        sR.add(sN);
        sR.add(id);
        sR.add(age);
        sN.addActionListener(this);
        id.addActionListener(this);
        age.addActionListener(this);
    }
    private void set_Help_Items()
    {
        JMenuItem au,cp;
        au= new JMenuItem("About us");
        cp= new JMenuItem("Change Password");
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
        if(button=="Search by Name")
        {
            p.showButton("search by name","p_name");
        }
        else if((button=="Search by ID")|| (button=="Search Record"))
        {
            p.showButton("search by id","p_id");
        }
        else if(button=="Search by Age")
        {
            p.showButton("search by age","p_id");
        }
        else if(button=="Change Password")
        {
            changePassword();
        }
    
    }
    
}
