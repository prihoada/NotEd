/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.project.noted.menus.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Myri
 */
public class About extends JDialog {
     static void createAndShowGUI() {
        
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
