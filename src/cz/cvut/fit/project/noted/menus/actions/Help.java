/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.project.noted.menus.actions;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Myri
 */
public class Help extends JDialog {
     static void createAndShowGUI() {

        JDialog dialog = new JDialog();
    
         
        JOptionPane.showMessageDialog(dialog, ""
                + "<html>Under Construction</html>", "Help", JOptionPane.PLAIN_MESSAGE);
           
}
    
}
