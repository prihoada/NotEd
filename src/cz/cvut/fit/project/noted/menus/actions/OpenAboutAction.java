package cz.cvut.fit.project.noted.menus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.*; 
import javax.swing.*; 

/**
 *
 * @author Adam Příhoda
 */
public class OpenAboutAction extends JPanel
                      implements ActionListener 
{

    public OpenAboutAction()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
            createAndShowGUI();

    }
    
     private static void createAndShowGUI() {
        
        JDialog dialog = new JDialog();
               
         
        JOptionPane.showMessageDialog(dialog, ""
                + "<html><font size=+2>NotEd</font><br>"
                + "Simple music editor that displays and edits music<br><br>"
                + "Created by:<br>Adam Příhoda,<br>David Vavřička,<br>Ondřej Altman,<br>Karel Šmejkal<br><br>"
                + "Additional help by: "
                + "<br>Petr Prokop,<br> Nguyen Viet Bach<br>"
                + "<br><i>Copyright 2014 NotEd team all rights reserved</i></html>", "About", JOptionPane.PLAIN_MESSAGE);
           
      
    }
    
}
