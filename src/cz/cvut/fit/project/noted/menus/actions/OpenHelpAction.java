package cz.cvut.fit.project.noted.menus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.*; 
import javax.swing.*; 

public class OpenHelpAction extends JPanel
                      implements ActionListener 
{

    public OpenHelpAction()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
            Help.createAndShowGUI();

    }
}