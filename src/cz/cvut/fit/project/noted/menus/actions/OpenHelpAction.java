package cz.cvut.fit.project.noted.menus.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class OpenHelpAction extends JDialog implements ActionListener {
  
  public OpenHelpAction(JFrame parent, String title) {
    super(parent, title, true);
    String message = "this is help";

    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(message));
    getContentPane().add(messagePane);
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("Close"); 
    buttonPane.add(button); 
    button.addActionListener(this);
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack(); 
    setLocationRelativeTo(null);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    setVisible(true); 
    dispose(); 
  }
  
}
