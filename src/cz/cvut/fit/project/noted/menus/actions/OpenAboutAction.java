package cz.cvut.fit.project.noted.menus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.*; 
import javax.swing.*; 

public class OpenAboutAction extends JPanel
                      implements ActionListener 
{

    public OpenAboutAction()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
            About.createAndShowGUI();

    }
         
    
}
