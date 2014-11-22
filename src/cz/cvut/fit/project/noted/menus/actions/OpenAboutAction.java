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
public class OpenAboutAction implements ActionListener
{

    public OpenAboutAction()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Create and set up the window.
        JFrame frame = new JFrame("About");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel = new JLabel("<html><hl>NotEd</hl><br><p>Simple music editor that displays and edits music</p><br>Created by:<br>Adam Příhoda,"
                + "<br>David Vavřička,<br>Ondřej Altman,<br>Karel Šmejkal<br><br>Copyright 2014 NotEd team all rights reserved</html>",SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        
        //Display the window.
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);    
    }
    
}
